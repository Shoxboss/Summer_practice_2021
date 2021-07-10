package com.practice.Utilts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Graph.Node;
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

            ArrayList<Node> nodes = graph.getVertices();
            ArrayList<Edge> edges = graph.getEdges();
            for (Edge edge : edges){
                cloneGraph.addEdge(edge.getStartName(), edge.getEndName(), edge.getWeight());
                Edge lastEdge = cloneGraph.getEdges().get(cloneGraph.getCountEdges() - 1);
                Node start = lastEdge.getStart();
                Node end = lastEdge.getEnd();
                start.setX(edge.getStart().getX());
                start.setY(edge.getStart().getY());
                end.setX(edge.getEnd().getX());
                end.setY(edge.getEnd().getY());
            }
        } catch (IOException e){

        }
        return cloneGraph;
    }
}
