package graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex Plate on 20.09.2018.
 */
public class KosarajuTest {

    @Test
    public void testKosaraju_EmptyGraph_NoErrors() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[0];

        List<List<Integer>> search = Kosaraju.search(adjList);
        assertTrue(search.isEmpty());
    }

    @Test
    public void testKosaraju_OneVertex_OneComponent() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[1];
        GraphUtils.initAdjList(adjList);

        List<List<Integer>> search = Kosaraju.search(adjList);
        assertEquals(1, search.size());
        assertEquals(1, search.get(0).size());
        assertEquals(0, (int) search.get(0).get(0));
    }

    @Test
    public void testKosaraju_TwoVertexOneWay_TwoComponents() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[2];
        GraphUtils.initAdjList(adjList);
        adjList[0].add(1);

        List<List<Integer>> search = Kosaraju.search(adjList);
        assertEquals(2, search.size());
        assertEquals(1, search.get(0).size());
        assertEquals(1, search.get(1).size());
        assertEquals(1, (int) search.get(0).get(0));
        assertEquals(0, (int) search.get(1).get(0));
    }

    @Test
    public void testKosaraju_TwoVertexTwoWays_OneComponent() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[2];
        GraphUtils.initAdjList(adjList);
        adjList[0].add(1);
        adjList[1].add(0);

        List<List<Integer>> search = Kosaraju.search(adjList);
        assertEquals(1, search.size());
        assertEquals(2, search.get(0).size());
        assertEquals(1, (int) search.get(0).get(0));
        assertEquals(0, (int) search.get(0).get(1));
    }

    @Test
    public void testKosaraju_DifficultGraph_ComponentsAreFound() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[10];
        GraphUtils.initAdjList(adjList);

        // First component
        adjList[0].add(1);
        adjList[1].add(2);
        adjList[2].add(0);

        // Second component
        adjList[3].addAll(Arrays.asList(4, 5));
        adjList[4].addAll(Arrays.asList(6, 0, 2));
        adjList[5].add(6);
        adjList[6].add(3);

        // Third component
        adjList[7].addAll(Arrays.asList(6, 8));
        adjList[8].addAll(Arrays.asList(7, 6));

        // Fourth component
        adjList[9].addAll(Arrays.asList(5, 8));

        List<List<Integer>> search = Kosaraju.search(adjList);

        assertEquals(4, search.size());
        assertEquals(3, search.get(0).size());
        assertEquals(4, search.get(1).size());
        assertEquals(2, search.get(2).size());
        assertEquals(1, search.get(3).size());
    }
}