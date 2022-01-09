package org.ddd.booking.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.ddd.booking.application.CargoBookingCommandService;
import org.ddd.booking.application.CargoBookingQueryService;
import org.ddd.booking.domain.model.aggregates.BookingId;
import org.ddd.booking.domain.model.aggregates.Cargo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cargo-booking")
public class CargoBookingController {
    private final CargoBookingCommandService commandService;
    private final CargoBookingQueryService queryService;

    public CargoBookingController(CargoBookingCommandService commandService,
                                  CargoBookingQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @ResponseBody
    public BookingId bookCargo(@RequestBody BookCargoResource resource) {
        return commandService.bookCargo(BookCargoCommandDTOAssembler.toCommandFromDTO(resource));
    }

    @GetMapping("/find-cargo")
    @ResponseBody
    public Cargo findByBookingId(@RequestParam("bookingId") String bookingId) {
        return queryService.find(bookingId);
    } // find-cargo?bookingId="1234"


}
