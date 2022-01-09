package org.ddd.booking.infrastructure;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CargoEventSource {
    @Output("cargoBookingChannel")
    MessageChannel cargoBooking();

    @Output("cargoRoutingChannel")
    MessageChannel cargoRouting();
}
