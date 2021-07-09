package com.practice.Utilts;

import com.practice.Graph.Graph;
import com.practice.Gui.Rib;

import java.util.ArrayList;

public class VisualizeCommand implements Command{
    //Класс отвечает именно за создание визуализации АЛГОРИТМА, а не отрисовку ребер и вершин на стартовом холсте

    public VisualizeCommand(){

    }


    @Override
    public Graph execute(){
        return new Graph();
    }

   /* @Override
    public Graph execute(ArrayList<Rib> ribs){
        return new Graph();
    }

    @Override
    public Graph execute(String filename) {
        return new Graph();
    }*/


}
