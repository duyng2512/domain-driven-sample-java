package org.ddd.booking.interfaces;

import org.ddd.booking.domain.model.commands.BookCargoCommand;

import java.sql.Date;

public class BookCargoCommandDTOAssembler {

    public static BookCargoCommand toCommandFromDTO(BookCargoResource resource) {
        return BookCargoCommand.builder()
                               .bookingAmount(resource.getBookingAmount())
                               .destArrivalDeadline(Date.valueOf(resource.getDestArrivalDeadline()))
                               .destLocation(resource.getDestLocation())
                               .originLocation(resource.getOriginLocation())
                               .build();
    }
}
