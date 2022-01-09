package org.ddd.booking.application;

import org.ddd.booking.infrastructure.CargoEventSource;
import org.ddd.booking.shareddomain.events.CargoBookedEvent;
import org.ddd.booking.shareddomain.events.CargoRoutedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

//@Service
//@EnableBinding(CargoEventSource.class)
public class CargoEventPublisherService {

    CargoEventSource cargoEventSource;

    public CargoEventPublisherService(CargoEventSource cargoEventSource){
        this.cargoEventSource = cargoEventSource;
    }

    @TransactionalEventListener
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent){
        cargoEventSource.cargoBooking()
                        .send(MessageBuilder.withPayload(cargoBookedEvent).build());
    }

    @TransactionalEventListener
    public void handleCargoRoutedEvent(CargoRoutedEvent cargoRoutedEvent){
        cargoEventSource.cargoRouting()
                        .send(MessageBuilder.withPayload(cargoRoutedEvent).build());
    }

}