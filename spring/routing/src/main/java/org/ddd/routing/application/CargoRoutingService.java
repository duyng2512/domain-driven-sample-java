package org.ddd.routing.application;

import org.ddd.routing.shared.TransitEdge;
import org.ddd.routing.shared.TransitPath;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CargoRoutingService {
    public TransitPath findOptimalRoute(String origin, String destination, String arrivalDeadline){
        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();
        List<String> UnLoadCode = new ArrayList<>(Arrays.asList("HK", "NY", "SIG", "CA"));
        List<String> LoadCode = new ArrayList<>(Arrays.asList("HN", "BK", "TW", "SZ"));

        for (int i=0;i<4;i++) {
            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber("V11");
            transitEdge.setFromUnLoCode(LoadCode.get(i));
            transitEdge.setToUnLoCode(UnLoadCode.get(i));
            transitEdge.setFromDate(new Date());
            transitEdge.setToDate(new Date());
            transitEdges.add(transitEdge);
        }
        transitPath.setTransitEdges(transitEdges);
        return transitPath;
    }
}
