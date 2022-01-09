package org.ddd.booking.domain.model.aggregates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ddd.booking.domain.model.commands.BookCargoCommand;
import org.ddd.booking.domain.model.commands.RouteCargoCommand;
import org.ddd.booking.domain.model.entities.Location;
import org.ddd.booking.domain.model.valueobjects.BookingAmount;
import org.ddd.booking.domain.model.valueobjects.CargoItinerary;
import org.ddd.booking.domain.model.valueobjects.Delivery;
import org.ddd.booking.domain.model.valueobjects.event.LastCargoHandledEvent;
import org.ddd.booking.domain.model.valueobjects.routing.RouteSpecification;
import org.ddd.lib.shareddomain.events.CargoBookedEvent;
import org.ddd.lib.shareddomain.events.CargoBookedEventData;
import org.ddd.lib.shareddomain.events.CargoRoutedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name = "Cargo.findAll",
        query = "select c from Cargo c"),
        @NamedQuery(name = "Cargo.findByBookingId",
                query = "select c from Cargo c where c.bookingId = :bookingId"),
        @NamedQuery(name = "Cargo.findAllBookingIds",
                query = "Select c.bookingId from Cargo c")})
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Cargo extends AbstractAggregateRoot<Cargo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private BookingId bookingId;

    @Embedded
    private BookingAmount bookingAmount;

    @Embedded
    private Location origin;

    @Embedded
    private RouteSpecification routeSpecification;

    @Embedded
    private CargoItinerary itinerary;

    @Embedded
    private Delivery delivery;

    public Cargo(BookCargoCommand command) {
        this.bookingId = new BookingId(command.getBookingId());
        this.routeSpecification = RouteSpecification.builder()
                                                    .origin(new Location(command.getOriginLocation()))
                                                    .destination(new Location(command.getDestLocation()))
                                                    .build();
        this.origin = this.routeSpecification.origin;
        this.itinerary = CargoItinerary.EMPTY_ITINERARY();
        this.bookingAmount = new BookingAmount(command.getBookingAmount());
        this.delivery = Delivery.derivedFrom(this.routeSpecification, this.itinerary, LastCargoHandledEvent.EMPTY);

        /* Emit Events */
        addDomainEvent(new CargoBookedEvent(
                new CargoBookedEventData(bookingId.getBookingId())));
    }

    public void assignToRoute(RouteCargoCommand routeCargoCommand) {
        this.itinerary = routeCargoCommand.getCargoItinerary();
        this.delivery = delivery.updateOnRouting(this.routeSpecification, this.itinerary);

        /* Emit Events */
        addDomainEvent(new CargoRoutedEvent(
                new CargoBookedEventData(bookingId.getBookingId())));
    }

    public void deriveDeliveryProgress(LastCargoHandledEvent lastCargoHandledEvent) {
        this.delivery = Delivery.derivedFrom(getRouteSpecification(), getItinerary(), lastCargoHandledEvent);
    }

    public void addDomainEvent(Object event) {
        registerEvent(event);
    }

}
