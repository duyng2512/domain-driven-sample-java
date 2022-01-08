package org.ddd.booking.domain.model.valueobjects.routing;

import lombok.Builder;
import org.ddd.booking.domain.model.entities.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Embeddable
@Builder
public class RouteSpecification {
    private static final long serialVersionId = 1L;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "spec_origin_id"))
    public Location origin;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "spec_destination_id"))
    public Location destination;

    @Temporal(TemporalType.DATE)
    @Column(name = "spec_arrival_deadline")
    @NotNull
    private Date arrivalDeadline;

}
