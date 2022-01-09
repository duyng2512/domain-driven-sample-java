package org.ddd.booking.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ddd.booking.domain.model.valueobjects.CargoItinerary;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteCargoCommand {

    private String cargoBookingId;
    private String originLocation;
    private String destinationLocation;
    private Date arrivalDeadline;
    private CargoItinerary cargoItinerary;

}
