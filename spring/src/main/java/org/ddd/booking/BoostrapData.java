package org.ddd.booking;

import lombok.extern.slf4j.Slf4j;
import org.ddd.booking.application.CargoBookingCommandService;
import org.ddd.booking.domain.model.aggregates.BookingId;
import org.ddd.booking.domain.model.commands.BookCargoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class BoostrapData implements ApplicationRunner {

    CargoBookingCommandService service;
    @Autowired
    public void setService(CargoBookingCommandService service) { this.service = service; }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BookCargoCommand command = BookCargoCommand.builder()
                                                   .originLocation("USA")
                                                   .destLocation("JAPAN")
                                                   .bookingAmount(100)
                                                   .destArrivalDeadline(new Date())
                                                   .build();
        BookingId bookingId = service.bookCargo(command);
        log.info("Cargo booked ID [" + bookingId.getBookingId() + "]");
    }
}
