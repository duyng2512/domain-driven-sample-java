package org.ddd.booking.infrastructure;

import org.ddd.booking.domain.model.aggregates.BookingId;
import org.ddd.booking.domain.model.aggregates.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Cargo findByBookingId(BookingId bookingId);
    List<BookingId> findAllBookingIds();
    List<Cargo> findAll();
}
