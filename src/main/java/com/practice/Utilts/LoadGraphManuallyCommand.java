package com.practice.Utilts;
import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Graph.Node;
import com.practice.Gui.*;

import java.util.ArrayList;

public class LoadGraphManuallyCommand implements Command{

    private ArrayList<Rib> ribs;

    public LoadGraphManuallyCommand(ArrayList<Rib> ribs){
        this.ribs = ribs;
    }
    @Override
    public Graph execute(){
        Graph graph = new Graph();
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
            //
        }
        return graph;
    }
}
