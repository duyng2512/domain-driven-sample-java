package org.ddd.booking.domain.model.valueobjects.event;

import lombok.Getter;
import org.ddd.booking.domain.model.entities.Location;
import org.ddd.booking.domain.model.valueobjects.Voyage;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class CargoHandlingActivity {
    private static final long serialVersionUID = 1L;

    @Column(name = "next_expected_handling_event_type")
    private String type;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "next_expected_location_id"))
    private Location location;

    @Column(name = "next_expected_voyage_id")
    private Voyage voyage;

}
