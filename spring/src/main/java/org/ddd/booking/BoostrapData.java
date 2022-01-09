package org.ddd.booking;

import lombok.extern.slf4j.Slf4j;
import org.ddd.booking.application.CargoBookingCommandService;
import org.ddd.booking.application.CargoBookingQueryService;
import org.ddd.booking.domain.model.aggregates.BookingId;
import org.ddd.booking.domain.model.aggregates.Cargo;
import org.ddd.booking.domain.model.commands.BookCargoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class BoostrapData implements ApplicationRunner {

    CargoBookingCommandService commandService;
    CargoBookingQueryService queryService;

    public BoostrapData(CargoBookingCommandService commandService,
                        CargoBookingQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        BookCargoCommand command = BookCargoCommand.builder()
                                                   .bookingId("7916c104-05f0-40c5-9936-ad8bfcc876d7")
                                                   .originLocation("USA")
                                                   .destLocation("JAPAN")
                                                   .bookingAmount(100)
                                                   .destArrivalDeadline(new Date())
                                                   .build();
        BookingId bookingId = commandService.bookCargo(command);
        log.info("Cargo booked ID [" + bookingId.getBookingId() + "]");
        Cargo cargo = queryService.find(bookingId.getBookingId());
        log.info("Cargo Query ID [" + bookingId.getBookingId() + "]" + cargo.toString());

    }
}
