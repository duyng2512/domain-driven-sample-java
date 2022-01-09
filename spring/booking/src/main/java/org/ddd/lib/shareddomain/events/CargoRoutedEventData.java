package org.ddd.lib.shareddomain.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargoRoutedEventData {
    private String bookingId;
}
