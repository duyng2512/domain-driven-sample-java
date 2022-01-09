package org.ddd.lib.shareddomain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class TransitEdge implements Serializable {
    private String voyageNumber;
    private String fromUnLoCode;
    private String toUnLoCode;
    private Date fromDate;
    private Date toDate;
}
