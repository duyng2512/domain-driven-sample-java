package org.ddd.routing.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitPath implements Serializable {
    private List<TransitEdge> transitEdges;
}
