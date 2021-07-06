package com.tests;

import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Graph.Node;
import com.practice.Utilts.BoruvkaAlg;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoruvkaTest {

    Graph g;
    BoruvkaAlg boruvka;

    @Before
    public void setUp() {
        g = new Graph();
        g.addEdge("a", "b", 3);
        g.addEdge("b", "c", 4);
        g.addEdge("a", "c", 1);
        boruvka = new BoruvkaAlg(g);
    }

    @Test
    public void getMstTest() {
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(new Node("a"), new Node("c"), 1));
        edges.add(new Edge(new Node("a"), new Node("b"), 3));
        boruvka.doAlgorithm();
        assertArrayEquals(edges.toArray(), boruvka.getMst().toArray());
    }

}
