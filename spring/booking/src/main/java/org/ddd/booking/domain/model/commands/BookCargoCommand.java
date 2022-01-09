package org.ddd.booking.domain.model.commands;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class BookCargoCommand {

    private String bookingId;
    private Integer bookingAmount;
    private String originLocation;
    private String destLocation;
    private Date destArrivalDeadline;

}
