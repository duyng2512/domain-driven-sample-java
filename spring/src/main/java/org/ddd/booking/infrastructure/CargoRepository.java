package org.ddd.booking.infrastructure;

import org.ddd.booking.domain.model.aggregates.BookingId;
import org.ddd.booking.domain.model.aggregates.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Cargo findByBookingId(BookingId BookingId);
    List<BookingId> findAllBookingIds();
    List<Cargo> findAll();
}
