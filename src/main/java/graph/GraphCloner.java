package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Plate on 23.09.2018.
 */
public class GraphCloner {
    public GraphNode clone(GraphNode original) {
        Map<GraphNode, GraphNode> nodes = new HashMap<>();

        Deque<GraphNode> queue = new ArrayDeque<>();
        queue.push(original);
        nodes.put(original, new GraphNode(original.value));

        while (!queue.isEmpty()) {
            GraphNode current = queue.pop();
            for (GraphNode neighbour : current.neighbours) {
                GraphNode copy = nodes.get(neighbour);
                if (copy == null) {
                    queue.push(neighbour);
                    nodes.put(neighbour, new GraphNode(neighbour.value));
                }
                nodes.get(current).neighbours.add(neighbour);
            }
        }

        return nodes.get(original);
    }
}
