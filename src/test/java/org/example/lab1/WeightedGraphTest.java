package org.example.lab1;

import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;


public class WeightedGraphTest  {

    @Test
    public void addEdge(){

        WeightedGraph<String> graph = new WeightedGraph<>(List.of("A", "B", "C"));
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);

        String expectedGraphString = "A -> [(B, 10.0)]"+ System.lineSeparator() +
                "B -> [(A, 10.0), (C, 15.0)]" + System.lineSeparator() +
                "C -> [(B, 15.0)]"+ System.lineSeparator();

        assertEquals(expectedGraphString, graph.toString());
    }

    @Test
    public void mst(){
        WeightedGraph<String> graph = new WeightedGraph<>(List.of("A", "B", "C"));
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        graph.addEdge("A", "C", 20);

        List<WeightedEdge> mst = graph.mst(0);

        assertEquals(2, mst.size());
        assertEquals("0 10.0> 1", mst.get(0).toString());
        assertEquals("1 15.0> 2", mst.get(1).toString());
    }

    @Test
    public void totalWeight() {
        List<WeightedEdge> path = List.of(new WeightedEdge(0, 1, 10), new WeightedEdge(1, 2, 15));
        double totalWeight = WeightedGraph.totalWeight(path);

        assertEquals(25.0, totalWeight, 0.001);
    }



}