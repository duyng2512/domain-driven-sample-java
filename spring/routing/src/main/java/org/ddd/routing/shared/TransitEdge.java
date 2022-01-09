package org.ddd.routing.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransitEdge implements Serializable {

    private String voyageNumber;
    private String fromUnLoCode;
    private String toUnLoCode;
    private Date fromDate;
    private Date toDate;

}
