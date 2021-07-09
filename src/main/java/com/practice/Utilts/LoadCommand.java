package com.practice.Utilts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Gui.Rib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LoadCommand implements Command{
    //Класс отвечает за загрузку графа из файла

    private String filename;

    public LoadCommand(String filename){
        this.filename = filename;
    }

    /*@Override
    public void execute(){
        return;
    }

    @Override
    public Graph execute(ArrayList<Rib> ribs){
        return new Graph();
    }*/

    @Override
    public Graph execute() {
        Graph graph = null;
        Graph cloneGraph = new Graph();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            String graphJson = Files.readString(Path.of(filename));
            graph = gson.fromJson(graphJson, Graph.class);

            ArrayList<Edge> edges = graph.getEdges();
            for (Edge edge : edges){
                cloneGraph.addEdge(edge.getStartName(), edge.getEndName(), edge.getWeight());
            }
        } catch (IOException e){

        }
        return cloneGraph;
    }
}
