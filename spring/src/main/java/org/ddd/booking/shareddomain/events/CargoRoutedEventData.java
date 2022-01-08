package org.ddd.booking.shareddomain.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargoRoutedEventData {
    private String bookingId;
}
