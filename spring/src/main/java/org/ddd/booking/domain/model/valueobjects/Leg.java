package org.ddd.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ddd.booking.domain.model.entities.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
public class Leg {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    public Long id;

    @Embedded
    private Voyage voyage;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "load_location_id"))
    private Location loadLocation;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "unload_location_id"))
    private Location unloadLocation;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "load_time")
    private Date loadTime;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "unload_time")
    private Date unloadTime;


}
