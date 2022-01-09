package org.ddd.routing.application;

import org.ddd.routing.shared.TransitEdge;
import org.ddd.routing.shared.TransitPath;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CargoRoutingService {
    public TransitPath findOptimalRoute(String origin, String destination, String arrivalDeadline){
        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();
        for (int i=0;i<4;i++) {
            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber("V11");
            transitEdge.setFromUnLoCode("CHK");
            transitEdge.setFromDate(new Date());
            transitEdge.setToDate(new Date());
            transitEdge.setToUnLoCode("NYC");
            transitEdges.add(transitEdge);
        }
        transitPath.setTransitEdges(transitEdges);
        return transitPath;
    }
}
