package com.practice.Utilts;

import com.practice.Graph.Graph;
import com.practice.Gui.Rib;

import java.util.ArrayList;

public class GenerateCommand implements Command{
    //Класс отвечает за генерацию случайного графа
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
