package org.ddd.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingAmount {
    @Column(name = "booking_amount", unique = false, updatable = false)
    private Integer bookingAmount;
}
