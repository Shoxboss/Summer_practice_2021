package com.practice.Utilts;

import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Graph.Node;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public class AlgorithmMemento {
        private Graph graphStorage;
        private ArrayList<Edge> mstStorage;
        private int countTreeStorage;

        public AlgorithmMemento(){
            this.graphStorage = (Graph) graph.clone();
            ArrayList<Edge> cloneMst = new ArrayList<>(mst.size());
            for (Edge edge : mst){
                cloneMst.add((Edge)edge.clone());
            }
            this.mstStorage = cloneMst;
            this.countTreeStorage = countTree;
        }

    }

    public BoruvkaAlg(Graph graph) {
        mst = new ArrayList<>();
        this.graph = graph;
        init();
    }


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

    public ArrayList<Edge> getMst() {
        return mst;
    }

    public int getCountTree(){
        return this.countTree;
    }

    public AlgorithmMemento save(){
        return new AlgorithmMemento();
    }

    public void restore(AlgorithmMemento snap) throws NullPointerException{
        if (snap.graphStorage == null) {
            Logger logger = LogManager.getLogger(BoruvkaAlg.class);
            logger.fatal("Невозможно восстановить состояние алгоритма");
            throw new NullPointerException();
        }
        this.graph = snap.graphStorage;
        this.mst = snap.mstStorage;
        countVertices = graph.getCountVertices();
        countEdges = graph.getCountEdges();
        vertices = graph.getVertices();
        edges = graph.getEdges();
        countTree = snap.countTreeStorage;
    }


}
