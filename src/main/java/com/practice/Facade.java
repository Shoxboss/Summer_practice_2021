package com.practice;

import java.util.ArrayList;

public class Facade {
    private Graph graph;
    private Algorithm algorithm;
    private ArrayList<Edge> currentMst;

    public Facade(Graph graph){
        this.graph = graph;
        this.algorithm = new BoruvkaAlg(graph);
        algorithm.printRes();
        this.currentMst = algorithm.getMst();
    }

    public Memento save(){
        ArrayList<Edge> cloneMst = new ArrayList<>(currentMst.size());
        for (Edge edge : currentMst){
            cloneMst.add((Edge)edge.clone());
        }
        return new FacadeMemento(graph, cloneMst);
    }

    public void executeCommand(Command command){
        return;
    }

    public void algorithmStep(){
        algorithm.algorithmStep();
        currentMst = algorithm.getMst();
    }

    //а пробросит ли дальше оно исключение?
    public void restore(FacadeMemento snap) throws NullPointerException{
        this.graph = snap.getGraph();
        this.currentMst = snap.getMst();
    }

    public void printMst(){
        System.out.println("Print MST:");
        for (Edge edge : currentMst){
            System.out.println(edge);
        }
    }
}
