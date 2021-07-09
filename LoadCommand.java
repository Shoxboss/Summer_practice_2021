package com.practice.Utilts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.Graph.Graph;
import com.practice.Gui.Rib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LoadCommand implements Command{
    //Класс отвечает за загрузку графа из файла
    @Override
    public void execute(){
        return;
    }

    @Override
    public Graph execute(ArrayList<Rib> ribs){
        return new Graph();
    }

    @Override
    public Graph execute(String filename) {
        Graph graph = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            String graphJson = Files.readString(Path.of(filename));
            graph = gson.fromJson(graphJson, Graph.class);
        } catch (IOException e){

        }
        return graph;
    }
}
