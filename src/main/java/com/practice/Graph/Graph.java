package com.practice.Graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements Cloneable{
    private ArrayList<Node> vertices;
    private ArrayList<Edge> edges;
    private int countVertices;
    private int countEdges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        countEdges = 0;
        countVertices = 0;
    }

    public Graph(ArrayList<Node> vertices, ArrayList<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        countEdges = edges.size();
        countVertices = vertices.size();
    }

    public int findVertex(String u) {
        for (Node nodes : vertices) {
            if (nodes.getName().equals(u)) {
                return vertices.indexOf(nodes);
            }
        }
        return -1;
    }

    public void addVertex(String v) {
        if (v == null) {
            throw new IllegalArgumentException("Нельзя добавить пустую вершину");
        }
        if (findVertex(v) == -1) {
            vertices.add(new Node(v));
            countVertices++;
        }
        else {
            System.out.println("Вершина с таким именем уже существует");
        }
    }

    /*public void addVertex(Node v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            countVertices++;
        }
    }*/


    /*public void addEdge(String u, String v, int w) {
        if (u.equals(v)) {
            return; // Дописать исключение IncorrectArgumentException
        }
        edges.add(new Edge(new Node(u), new Node(v), w));
        addVertex(u);
        addVertex(v);
        countEdges++;
    }*/

    public void addEdge(String u, String v, int w) {
        if (u == null || v == null) {
            throw new IllegalArgumentException("Нельзя добавить ребро с пустой вершиной");
        }
        if (u.equals(v)) {
            System.out.println("Начальная и конечная вершины одинаковые");
            return;
        }
        int indexStart = findVertex(u);
        int indexEnd = findVertex(v);
        Node start;
        Node end;
        if (indexStart != -1) {
            start = vertices.get(indexStart);
        }
        else {
            start = new Node(u);
            vertices.add(start);
            countVertices++;
        }
        if (indexEnd != -1) {
            end = vertices.get(indexEnd);
        }
        else {
            end = new Node(v);
            vertices.add(end);
            countVertices++;
        }
        Edge newEdge = new Edge(start, end, w);
        for (Edge edge: edges) {
            if ((edge.getStartName().equals(u) & edge.getEndName().equals(v)) ||
                    (edge.getStartName().equals(v) & edge.getEndName().equals(u))){
                System.out.println("Такое ребро уже существует");
                return;
            }
        }
        edges.add(newEdge);
        countEdges++;
        /*if (edges.contains(newEdge)) {
            System.out.println("Такое ребро уже есть");
        }
        else {
            edges.add(newEdge);
            countEdges++;
        }*/
    }

    public void removeVertex(String v) {
        if (v == null) {
            throw new IllegalArgumentException("Нельзя удалить пустую вершину");
        }
        int nodeIndex = findVertex(v);
        if (nodeIndex != -1) {
            vertices.remove(nodeIndex);
            countVertices--;
            Iterator<Edge> edgeIterator = edges.iterator();
            while (edgeIterator.hasNext()) {
                Edge edgeNext = edgeIterator.next();
                if (edgeNext.getStartName().equals(v) | edgeNext.getEndName().equals(v)) {
                    edgeIterator.remove();
                    countEdges--;
                }
            }
        }
    }

    public void removeEdge(String start, String end, int w) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Нельзя удалить ребро с пустой вершиной");
        }
        if (start.equals(end)) {
            System.out.println("Начальная и конечная вершины одинаковые");
            return;
        }
        Edge remEdge = new Edge(new Node(start), new Node(end), w);
        if (edges.remove(remEdge)) {
            countEdges--;
        }
    }

    public ArrayList<Node> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getCountVertices() {
        return countVertices;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public void printVertices() {
        for (Node vertex: vertices) {
            System.out.println(vertex.getName() + " " + vertex.getComponent());
        }
    }

    public void printEdges() {
        for (Edge edge: edges) {
            System.out.println(edge.toString());
        }
    }

    /*public void setVertices(ArrayList<Node> vertices) {
        this.vertices = vertices;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public void setCountVertices(int countVertices) {
        this.countVertices = countVertices;
    }

    public void setCountEdges(int countEdges) {
        this.countEdges = countEdges;
    }*/

    public Object clone()
    {
        Graph graph = new Graph((ArrayList<Node>) this.getVertices().clone(), (ArrayList<Edge>) this.getEdges().clone());
        return graph;
    }

}