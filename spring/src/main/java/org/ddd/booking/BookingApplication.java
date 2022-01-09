package org.ddd.booking;

import lombok.extern.slf4j.Slf4j;
import org.ddd.booking.domain.model.aggregates.BookingId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

}
