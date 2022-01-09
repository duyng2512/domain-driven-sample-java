package org.ddd.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voyage {
    @Column(name = "voyage_id", insertable = false, updatable = false)
    private String voyageNumber;

    /*
    insertable = false, updatable = false

    You would do that when the responsibility of creating/updating the referenced
        column isn't in the current entity, but in another entity.
     */
}
