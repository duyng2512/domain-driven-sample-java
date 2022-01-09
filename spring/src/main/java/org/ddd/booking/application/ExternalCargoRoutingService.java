package org.ddd.booking.application;

import org.ddd.booking.domain.model.entities.Location;
import org.ddd.booking.domain.model.valueobjects.CargoItinerary;
import org.ddd.booking.domain.model.valueobjects.Leg;
import org.ddd.booking.domain.model.valueobjects.Voyage;
import org.ddd.booking.domain.model.valueobjects.routing.RouteSpecification;
import org.ddd.booking.shareddomain.TransitEdge;
import org.ddd.booking.shareddomain.TransitPath;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalCargoRoutingService {

    public CargoItinerary fetchRouteForSpecification(RouteSpecification specification){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();

        params.put("origin", specification.getOrigin());
        params.put("destination", specification.getDestination());
        params.put("arrivalDeadline", specification.getArrivalDeadline().toString());

        TransitPath transitPath = restTemplate.getForObject("TEST", TransitPath.class, params);
        assert transitPath != null;
        List<Leg> legs = new ArrayList<>(transitPath.getTransitEdges().size());
        for (TransitEdge edge : transitPath.getTransitEdges()){
            legs.add(toLeg(edge));
        }
        return new CargoItinerary(legs);
    }

    private Leg toLeg(TransitEdge edge) {
        Voyage voyage = new Voyage(edge.getVoyageNumber());
        Location FromLocation = new Location(edge.getFromUnLoCode());
        Location ToLocation = new Location(edge.getToUnLoCode());
        Date fromDate = edge.getFromDate();
        Date toDate = edge.getToDate();

        return Leg.builder()
                  .voyage(voyage)
                  .loadLocation(FromLocation)
                  .unloadLocation(ToLocation)
                  .loadTime(fromDate)
                  .unloadTime(toDate)
                  .build();
    }
}
