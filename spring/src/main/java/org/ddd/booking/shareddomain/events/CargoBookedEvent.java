package org.ddd.booking.shareddomain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CargoBookedEvent {
    CargoBookedEventData cargoBookedEventData;
}
