package org.ddd.routing.interfaces;

import org.ddd.routing.application.CargoRoutingService;
import org.ddd.routing.shared.TransitPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cargo-route")
public class CargoRoutingController {
    private final CargoRoutingService routingService;
    public CargoRoutingController(CargoRoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/optimal")
    @ResponseBody
    public TransitPath findOptimalRoute( @PathVariable(value = "origin", required = false) String originUnLoCode,
                                         @PathVariable(value = "destination", required = false) String destinationUnLoCode,
                                         @PathVariable(value = "deadline", required = false) String deadline){
        return routingService.findOptimalRoute(originUnLoCode, destinationUnLoCode, deadline);
    }
}