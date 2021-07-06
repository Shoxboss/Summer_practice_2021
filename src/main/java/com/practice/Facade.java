package com.practice;

import java.util.ArrayList;

public class Facade {
    private Graph graph = null;
    private BoruvkaAlg algorithm;
    private CareTaker careTaker;
    private Command command;

    public Facade(Algorithm algorithm){
        this.algorithm = (BoruvkaAlg) algorithm;
        this.careTaker = new CareTaker(this.algorithm);
    }

    public void setCommand(Command command){
        this.command = command;
    }

    public void doAlgorithm() {
        while( algorithm.getCountTree() > 1 ){
            careTaker.backup();
            algorithm.algorithmStep();
            System.out.println("Mst:");
            algorithm.printRes();
        }

    }

    public void debug(){
        System.out.println("Debug Mst:");
        careTaker.undo();
        algorithm.printRes();
    }

    //вызывается после нажатия кнопки добавления вершины
    public void addVertexManually(){
        //вызывается конструктор вершины вершины и она добавляется в граф
        //вызывается метод отрисовки вершины на сцене
    }

    //вызывается после нажатия кнопки добавления ребра
    public void addEdgeManually(){
        //аналогично вершине

    }

    //вызывается после нажатия кнопки удаления вершины
    public void removeVertexManually(){
        //вызывается метод удаления вершины
        //вызывается метод отрисовки вершины на сцене
    }

    //вызывается после нажатия кнопки удаления ребра
    public void removeEdgeManually(){
        //аналогично вершине

    }

    public void createGraph(){
        //здесь вызывается одна из команд создания графа
    }

    public void initBoruvkaAlgorithm(){
        //создание алгоритма по существующему графу внутри фасада
    }


}
