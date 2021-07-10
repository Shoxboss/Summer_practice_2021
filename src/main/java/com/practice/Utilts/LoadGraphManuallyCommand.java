package com.practice.Utilts;
import com.practice.Graph.Graph;
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
        }
        return graph;
    }
}
