package com.practice.Utilts;
import com.practice.Graph.Graph;
import com.practice.Gui.*;

import java.util.ArrayList;

public class LoadGraphManuallyCommand implements Command{
    @Override
    public Graph execute(ArrayList<Rib> ribs){
        Graph graph = new Graph();
        for (Rib rib : ribs){
            graph.addEdge(rib.getSourceVertex().getId(), rib.getTargetVertex().getId(), rib.getWeigth().intValue());
        }
        return graph;
    }

    @Override
    public void execute(){
        return;
    }

    @Override
    public Graph execute(String filename) {
        return new Graph();
    }
}
