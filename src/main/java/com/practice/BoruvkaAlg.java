package com.practice;

import java.util.ArrayList;
import java.util.Arrays;

public class BoruvkaAlg implements Algorithm, Cloneable {

    private Graph graph;
    private ArrayList<Edge> mst;
    private int countVertices;
    private int countEdges;
    private int countTree;
    private ArrayList<Node> vertices;
    private ArrayList<Edge> edges;
    private Edge[] minEdges;


    /*public BoruvkaAlg() {
        mst = new ArrayList<>();
        init();
    }*/

    public BoruvkaAlg(Graph graph) {
        mst = new ArrayList<>();
        this.graph = graph;
        init();
    }

   /* //IT WORKS!!!!
    @Deprecated
    public void run(Graph graph) {
        int countVertices = graph.getCountVertices();
        int countEdges = graph.getCountEdges();
        ArrayList<Node> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();
        Edge[] minEdges = new Edge[countVertices];
        int n = 0;
        for (Node vertex: vertices) {
            vertex.setComponent(n);
            n++;
        }

        //Debug
        for (Edge edge: edges) {
            System.out.println(edge.getStartName() + " " + edge.getStart().getComponent());
            System.out.println(edge.getEndName() + " " + edge.getEnd().getComponent());
            System.out.println("-------");
        }
        //

        int countTree = countVertices;

        while (countTree > 1) {
            Arrays.fill(minEdges, null);
            for (Edge edge: edges) {
                int comp1 = edge.getStart().getComponent();
                int comp2 = edge.getEnd().getComponent();
                if (comp1 != comp2) {
                    if (minEdges[comp1] == null || edges.get(edges.indexOf(minEdges[comp1])).getWeight() > edge.getWeight()) {
                        minEdges[comp1] = edge;
                    }
                    if (minEdges[comp2] == null || edges.get(edges.indexOf(minEdges[comp2])).getWeight() > edge.getWeight()) {
                        minEdges[comp2] = edge;
                    }
                }
            }

            //Debug
            for (int i = 0; i < minEdges.length; i++) {
                if (minEdges[i] != null) {
                    System.out.println(i + ": " + minEdges[i].toString());
                }
            }
            //

            for (Edge minEdge: minEdges) {
                if (minEdge != null) {
                    int comp1 = minEdge.getStart().getComponent();
                    int comp2 = minEdge.getEnd().getComponent();
                    if (comp1 != comp2) {
                        mst.add(minEdge);
                        changeComponent(comp1, comp2, minEdge);
                        countTree--;
                    }
                }
            }

            //Debug
            for (Edge edge: edges) {
                System.out.println("After changes");
                System.out.println(edge.getStartName() + " " + edge.getStart().getComponent());
                System.out.println(edge.getEndName() + " " + edge.getEnd().getComponent());
                System.out.println("-------");
            }

            //
            System.out.println("CountTree = " + countTree);
            //
        }
    }*/

    private void changeComponent(int compStart, int compEnd, Edge edge) {
        if (compStart > compEnd) {
            edge.getStart().setComponent(compEnd);
        }
        else {
            edge.getEnd().setComponent(compStart);
        }
    }

    private void init() {
        countVertices = graph.getCountVertices();
        countEdges = graph.getCountEdges();
        vertices = graph.getVertices();
        edges = graph.getEdges();
        minEdges = new Edge[countVertices];
        countTree = countVertices;
        int n = 0;
        for (Node vertex: vertices) {
            vertex.setComponent(n);
            n++;
        }
    }

    @Override
    public void algorithmStep() {
        Arrays.fill(minEdges, null);
        for (Edge edge: edges) {
            int comp1 = edge.getStart().getComponent();
            int comp2 = edge.getEnd().getComponent();
            if (comp1 != comp2) {
                if (minEdges[comp1] == null || edges.get(edges.indexOf(minEdges[comp1])).getWeight() > edge.getWeight()) {
                    minEdges[comp1] = edge;
                }
                if (minEdges[comp2] == null || edges.get(edges.indexOf(minEdges[comp2])).getWeight() > edge.getWeight()) {
                    minEdges[comp2] = edge;
                }
            }
        }

        //Debug
        /*for (int i = 0; i < minEdges.length; i++) {
            if (minEdges[i] != null) {
                System.out.println(i + ": " + minEdges[i].toString());
            }
        }*/
        //

        for (Edge minEdge: minEdges) {
            if (minEdge != null) {
                int comp1 = minEdge.getStart().getComponent();
                int comp2 = minEdge.getEnd().getComponent();
                if (comp1 != comp2) {
                    mst.add(minEdge);
                    changeComponent(comp1, comp2, minEdge);
                    countTree--;
                }
            }
        }
    }

    @Override
    public void doAlgorithm() {
        while (countTree > 1) {
            algorithmStep();
        }
    }

    @Override
    public void printRes() {
        for (Edge edge: mst) {
            System.out.println(edge.toString());
        }
    }

    @Override
    public ArrayList<Edge> getMst() {
        return mst;
    }
}
