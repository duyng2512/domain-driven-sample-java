package org.ddd.lib.shareddomain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CargoRoutedEvent {
    CargoBookedEventData cargoBookedEventData;
}
