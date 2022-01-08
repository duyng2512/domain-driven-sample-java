package org.ddd.booking.domain.model.valueobjects;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.ddd.booking.domain.model.entities.Location;
import org.ddd.booking.domain.model.valueobjects.event.CargoHandlingActivity;
import org.ddd.booking.domain.model.valueobjects.event.LastCargoHandledEvent;
import org.ddd.booking.domain.model.valueobjects.routing.RouteSpecification;
import org.ddd.booking.domain.model.valueobjects.routing.RoutingStatus;
import org.ddd.booking.domain.model.valueobjects.routing.TransportStatus;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Getter
@Setter
public class Delivery {
    public static  final Date ETA_UNKNOWN = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "routing_status")
    private RoutingStatus routingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_status")
    private TransportStatus transportStatus;

    @AttributeOverride(name = "unLocCode", column = @Column(name = "last_known_location_id"))
    private Location lastKnownLocation;

    @Column(name = "current_voyage_id")
    private Voyage currentVoyage;

    @Embedded
    private LastCargoHandledEvent lastEvent;

    public static final CargoHandlingActivity NO_ACTIVITY = new CargoHandlingActivity();

    @Embedded
    private CargoHandlingActivity nextExpectedActivity;

    /* Business Logic */
    public Delivery(LastCargoHandledEvent lastEvent, CargoItinerary itinerary,
                    RouteSpecification routeSpecification){
       this.lastEvent = lastEvent;
       this.routingStatus = calculateRoutingStatus(itinerary);
       this.transportStatus = calculateTransportStatus();
       this.lastKnownLocation = calculateLastKnownLocation();
       this.currentVoyage = calculateCurrentVoyage();
    }

    public Delivery updateOnRouting(RouteSpecification routeSpecification, CargoItinerary itinerary){
        return new Delivery(this.lastEvent, itinerary, routeSpecification);
    }

    public static Delivery derivedFrom(RouteSpecification routeSpecification, CargoItinerary itinerary,
                                       LastCargoHandledEvent lastEvent){
        return new Delivery(lastEvent, itinerary, routeSpecification);
    }

    private RoutingStatus calculateRoutingStatus(CargoItinerary itinerary){
        if (itinerary == null || itinerary.equals(CargoItinerary.EMPTY_ITINERARY())){
            return RoutingStatus.NOT_ROUTED;
        } else {
            return RoutingStatus.ROUTED;
        }
    }

    private TransportStatus calculateTransportStatus(){
        log.info("Transport Status for last event " + lastEvent.getHandlingEventType());
        if (lastEvent.getHandlingEventType() == null) return TransportStatus.NOT_RECEIVED;

        switch (lastEvent.getHandlingEventType()){
            case "LOAD": return TransportStatus.ONBOARD_CARRIER;
            case "UNLOAD":
            case "RECEIVE":
            case "CUSTOMS": return TransportStatus.IN_PORT;
            case "CLAIM": return TransportStatus.CLAIMED;
            default: return TransportStatus.UNKNOWN;
        }
    }

    private Location calculateLastKnownLocation(){
        if (lastEvent != null){
            return new Location(lastEvent.getHandlingEventLocation());
        }
        return null;
    }

    private Voyage calculateCurrentVoyage(){
        if (this.transportStatus.equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null){
            return Voyage.builder()
                         .voyageNumber(lastEvent.getHandlingEventVoyage())
                         .build();
        }
        return null;
    }
}
