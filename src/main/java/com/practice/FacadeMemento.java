package com.practice;

import java.util.ArrayList;

public class FacadeMemento implements Memento{
    private Graph graph;
    private ArrayList<Edge> currentMst;

    public FacadeMemento(Graph graph, ArrayList<Edge> currentMst){
        this.graph = (Graph) graph.clone();
        this.currentMst = currentMst;
    }

    @Override
    public Graph getGraph() throws NullPointerException{
        if (graph == null)
            throw new NullPointerException();
        return graph;
    }

    @Override
    public ArrayList<Edge> getMst() throws NullPointerException{
        if (currentMst == null)
            throw new NullPointerException();
        return currentMst;
    }
}
