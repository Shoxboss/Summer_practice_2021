package com.tests;

import com.practice.Graph.*;
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
    public void findExistingVertex() {
        assertEquals(1, g.findVertex("b"));
    }

    @Test
    public void findNonExistentVertex() {
        assertEquals(-1, g.findVertex("g"));
        g.addVertex("g");
    }

    @Test
    public void findNullVertex() {
        thrown.expect(IllegalArgumentException.class);
        g.findVertex(null);
    }

    @Test
    public void addNewVertex() {
        g.addVertex("k");
        assertEquals(4, g.getCountVertices());
    }

    @Test
    public void addVertexThatAlreadyExists() {
        g.addVertex("a");
        assertEquals(3, g.getCountVertices());
    }

    @Test
    public void addNullVertex() {
        thrown.expect(IllegalArgumentException.class);
        g.addVertex(null);
    }

    @Test
    public void addNewEdge() {
        g.addEdge("a", "g", 5);
        assertEquals(4, g.getCountEdges());
    }

    @Test
    public void addEdgeThatAlreadyExists() {
        g.addEdge("a", "b", 3);
        assertEquals(3, g.getCountEdges());
    }

    @Test
    public void addEdgeWithNullVertex() {
        thrown.expect(IllegalArgumentException.class);
        g.addEdge(null, null, 3);
    }

    @Test
    public void addEdgeWithZeroWeight() {
        thrown.expect(IllegalArgumentException.class);
        g.addEdge("d", "g", 0);
    }

    @Test
    public void removeVertex() {
        g.removeVertex("a");
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("b"));
        nodes.add(new Node("c"));
        assertArrayEquals(nodes.toArray(), g.getVertices().toArray());
    }

    @Test
    public void removeNonExistentVertex() {
        g.removeVertex("d");
        assertEquals(3, g.getCountVertices());
    }

    @Test
    public void removeNullVertex() {
        thrown.expect(IllegalArgumentException.class);
        g.removeVertex(null);
    }

    @Test
    public void removeEdge() {
        g.removeEdge("a", "c", 1);
        assertEquals(2, g.getCountEdges());
    }

    @Test
    public void removeNonExistentEdge() {
        g.removeEdge("b", "d", 5);
        assertEquals(3, g.getCountEdges());
    }

    @Test
    public void removeNullEdge() {
        thrown.expect(IllegalArgumentException.class);
        g.removeEdge(null, null, 2);
    }

    @Test
    public void removeEdgeWithSameStartAndEnd() {
        g.removeEdge("a", "a", 4);
        assertEquals(3, g.getCountEdges());
    }

    @Test
    public void getVertices() {
        ArrayList< Node > nodes = new ArrayList<>();
        nodes.add(new Node("a"));
        nodes.add(new Node("b"));
        nodes.add(new Node("c"));
        assertArrayEquals(nodes.toArray(), g.getVertices().toArray());
    }

    @Test
    public void getEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(new Node("a"), new Node("b"), 3));
        edges.add(new Edge(new Node("b"), new Node("c"), 4));
        edges.add(new Edge(new Node("a"), new Node("c"), 1));
        assertArrayEquals(edges.toArray(), g.getEdges().toArray());
    }

    @Test
    public void getCountVertices() {
        assertEquals(3, g.getCountVertices());
    }

    @Test
    public void getCountVerticesAfterRemoveVertex() {
        g.removeVertex("a");
        assertEquals(2, g.getCountVertices());
    }

    @Test
    public void getCountVerticesAfterRemoveEdge() {
        g.removeEdge("a", "b", 3);
        assertEquals(3, g.getCountVertices());
    }

    @Test
    public void getCountEdges() {
        assertEquals(3, g.getCountEdges());
    }

    @Test
    public void getCountEdgesAfterRemoveEdge() {
        g.removeEdge("a", "c", 1);
        assertEquals(2, g.getCountEdges());
    }

    @Test
    public void getCountEdgesAfterRemoveVertex() {
        g.removeVertex( "c");
        assertEquals(1, g.getCountEdges());
    }

}