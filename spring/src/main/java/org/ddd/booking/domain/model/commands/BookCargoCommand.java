package org.ddd.booking.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookCargoCommand {

    private String bookingId;
    private Integer bookingAmount;
    private String originLocation;
    private String destLocation;
    private Date destArrivalDeadline;

}
