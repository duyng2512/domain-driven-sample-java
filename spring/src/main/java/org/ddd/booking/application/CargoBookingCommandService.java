package org.ddd.booking.application;

import lombok.extern.slf4j.Slf4j;
import org.ddd.booking.domain.model.aggregates.BookingId;
import org.ddd.booking.domain.model.aggregates.Cargo;
import org.ddd.booking.domain.model.commands.BookCargoCommand;
import org.ddd.booking.domain.model.commands.RouteCargoCommand;
import org.ddd.booking.domain.model.valueobjects.CargoItinerary;
import org.ddd.booking.infrastructure.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@Slf4j
public class CargoBookingCommandService {

    private final CargoRepository cargoRepository;
    private ExternalCargoRoutingService externalCargoRoutingService;

    public CargoBookingCommandService(CargoRepository cargoRepository,
                                      ExternalCargoRoutingService externalCargoRoutingService) {
        this.cargoRepository = cargoRepository;
        this.externalCargoRoutingService = externalCargoRoutingService;
    }

    public BookingId bookCargo(BookCargoCommand bookCargoCommand){
        String random = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
        log.info("Random is " + random);
        bookCargoCommand.setBookingId(random);
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.save(cargo);
        return new BookingId(random);
    }

    public void routeCargo(RouteCargoCommand routeCargoCommand){
        Cargo cargo = cargoRepository.findByBookingId(new BookingId(routeCargoCommand.getCargoBookingId()));
        CargoItinerary itinerary = CargoItinerary.EMPTY_ITINERARY();
        routeCargoCommand.setCargoItinerary(itinerary);
        cargo.assignToRoute(routeCargoCommand);
        cargoRepository.save(cargo);
    }

}
