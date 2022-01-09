package org.ddd.routing.interfaces;

import org.ddd.routing.application.CargoRoutingService;
import org.ddd.routing.shared.TransitPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cargo-route")
public class CargoRoutingController {
    private CargoRoutingService routingService;

    public CargoRoutingController(CargoRoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/optimal")
    @ResponseBody
    public TransitPath
}
