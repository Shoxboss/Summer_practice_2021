package com.tests;

import com.practice.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GraphTest {

    Graph g;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        g = new Graph();
        g.addEdge("a", "b", 3);
        g.addEdge("b", "c", 4);
        g.addEdge("a", "c", 1);

    }

    @Test
    public void findVertexTest() {
        assertEquals(1, g.findVertex("b"));
        assertEquals(-1, g.findVertex("g"));
        g.addVertex("g");
        assertEquals(3, g.findVertex("g"));
    }

    @Test
    public void addVertexTest() {
        g.addVertex("k");
        assertEquals(3, g.findVertex("k"));
        assertEquals(4, g.getCountVertices());
        g.addVertex("k");
        assertEquals(4, g.getCountVertices());
        thrown.expect(IllegalArgumentException.class);
        g.addVertex(null);
    }

    @Test
    public void addEdgeTest() {
        g.addEdge("a", "g", 5);
        assertEquals(4, g.getCountEdges());
        g.addEdge("a", "g", 6);
        assertEquals(5, g.getCountEdges());
        g.addEdge("a", "g", 5);
        assertEquals(5, g.getCountEdges());
        g.addEdge("a", "a", 7);
        assertEquals(5, g.getCountEdges());
        thrown.expect(IllegalArgumentException.class);
        g.addEdge(null, null, 3);
    }

    @Test
    public void removeVertexTest() {
        g.removeVertex("a");
        assertEquals(-1, g.findVertex("a"));
        assertEquals(2, g.getCountVertices());
        assertEquals(1, g.getCountEdges());
        thrown.expect(IllegalArgumentException.class);
        g.removeVertex(null);
    }

    @Test
    public void removeEdgeTest() {
        g.removeEdge("a", "c", 1);
        assertEquals(2, g.getCountEdges());
        assertEquals(3, g.getCountVertices());
        g.removeEdge("b", "c", 1);
        assertEquals(2, g.getCountEdges());
        assertEquals(3, g.getCountVertices());
        g.removeEdge("a", "a", 0);
        assertEquals(2, g.getCountEdges());
        assertEquals(3, g.getCountVertices());
        thrown.expect(IllegalArgumentException.class);
        g.removeEdge(null, null, 2);
    }

    @Test
    public void getVerticesTest() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));
        nodes.add(new Node("b"));
        nodes.add(new Node("c"));
        assertArrayEquals(nodes.toArray(), g.getVertices().toArray());
        g.addEdge("g", "d", 9);
        nodes.add(new Node("g"));
        nodes.add(new Node("d"));
        assertArrayEquals(nodes.toArray(), g.getVertices().toArray());
    }

    @Test
    public void getEdgesTest() {
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(new Node("a"), new Node("b"), 3));
        edges.add(new Edge(new Node("b"), new Node("c"), 4));
        edges.add(new Edge(new Node("a"), new Node("c"), 1));
        assertArrayEquals(edges.toArray(), g.getEdges().toArray());
        g.removeVertex("a");
        edges.remove(0);
        edges.remove(1);
        assertArrayEquals(edges.toArray(), g.getEdges().toArray());
    }

    @Test
    public void getCountVerticesTest() {
        assertEquals(3, g.getCountVertices());
        g.removeVertex("a");
        assertEquals(2, g.getCountVertices());
        g.removeVertex("b");
        assertEquals(1, g.getCountVertices());
    }

    @Test
    public void getCountEdgesTest() {
        assertEquals(3, g.getCountEdges());
        g.removeVertex("a");
        assertEquals(1, g.getCountEdges());
        g.addEdge("b", "d", 10);
        assertEquals(2, g.getCountEdges());
    }

}