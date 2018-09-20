package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex Plate on 20.09.2018.
 */
public class Kosaraju {
    public static List<List<Integer>> search(List<Integer>[] adjList) {
        List<Integer>[] invertedAdjList = GraphUtils.invertAdjList(adjList);

        boolean[] invertedVisited = new boolean[adjList.length];

        // Use linkedList because of prepend operation
        List<Integer> path = new LinkedList<>();

        // First DFS
        Deque<Integer> stack = new ArrayDeque<>();
        int pointer = -1;
        while (pointer < invertedVisited.length - 1) {
            if (!invertedVisited[++pointer]) {
                stack.push(pointer);
                invertedVisited[pointer] = true;
            }

            while (!stack.isEmpty()) {
                Integer vertex = stack.pop();
                path.add(0, vertex);

                for (int adjVertex : invertedAdjList[vertex]) {
                    if (!invertedVisited[adjVertex]) {
                        stack.push(adjVertex);
                        invertedVisited[adjVertex] = true;
                    }
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        // Second DFS
        boolean[] visited = new boolean[adjList.length];
        while (!path.isEmpty()) {
            Integer start = path.remove(0);
            List<Integer> component = new ArrayList<>();
            stack.push(start);
            component.add(start);
            visited[start] = true;
            while (!stack.isEmpty()) {
                Integer vertex = stack.pop();
                for (Integer adjVertex : adjList[vertex]) {
                    if (!visited[adjVertex]) {
                        component.add(adjVertex);
                        path.remove(adjVertex);
                        visited[adjVertex] = true;
                        stack.push(adjVertex);
                    }
                }
            }
            res.add(component);
        }
        return res;
    }
}
