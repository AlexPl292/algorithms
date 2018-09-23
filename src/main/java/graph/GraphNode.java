package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Plate on 23.09.2018.
 */
public class GraphNode {
    int value;

    List<GraphNode> neighbours = new ArrayList<>();

    public GraphNode(int value) {
        this.value = value;
    }
}
