package org.ddd.booking.domain.model.aggregates;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingId implements Serializable {
    @Column(name="booking_id")
    private String bookingId;
}
