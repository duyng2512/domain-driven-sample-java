package org.ddd.lib.shareddomain;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Data
public class TransitPath {
    public List<TransitEdge> transitEdges = new ArrayList<>();
}
