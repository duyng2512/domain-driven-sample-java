package org.ddd.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CargoItinerary {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id") // cargo_id reside in LEG table
    @OrderBy("loadTime")
    private List<Leg> legs = Collections.emptyList();

    public static CargoItinerary EMPTY_ITINERARY (){
        return new CargoItinerary();
    }

}
