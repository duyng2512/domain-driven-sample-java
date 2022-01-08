package org.ddd.booking.shareddomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Data
public class TransitPath {
    public List<TransitEdge> transitEdges = new ArrayList<>();
}
