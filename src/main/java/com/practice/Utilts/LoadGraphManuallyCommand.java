package com.practice.Utilts;
import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Graph.Node;
import com.practice.Gui.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadGraphManuallyCommand implements Command{

    private ArrayList<Rib> ribs;
    private HashMap<String, Vertex> verticeDict;

    public LoadGraphManuallyCommand(ArrayList<Rib> ribs, HashMap<String, Vertex> verticeDict){
        this.ribs = ribs;
        this.verticeDict = verticeDict;
    }
    @Override
    public Graph execute(){
        Graph graph = new Graph();
        /*for (Vertex value : verticeDict.values()){
            graph.addVertex(value.getId());
            Node cur = graph.getVertices().get(graph.getCountVertices() - 1);
            cur.setX(value.getX());
            cur.setY(value.getY());
        }*/
        for (Rib rib : ribs){
            graph.addEdge(rib.getSourceVertex().getId(), rib.getTargetVertex().getId(), rib.getWeight().intValue());
            // experimental
            Edge edge = graph.getEdges().get(graph.getCountEdges() - 1);
            Node start = edge.getStart();
            Node end = edge.getEnd();
            start.setX(rib.getSourceVertex().getX());
            start.setY(rib.getSourceVertex().getY());
            end.setX(rib.getTargetVertex().getX());
            end.setY(rib.getTargetVertex().getY());

        }
        return graph;
    }
}
