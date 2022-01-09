package org.ddd.booking.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCargoResource {

    private Integer bookingAmount;
    private String originLocation;
    private String destLocation;
    private LocalDate destArrivalDeadline;

}
