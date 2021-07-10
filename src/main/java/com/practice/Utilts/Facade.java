package com.practice.Utilts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.Graph.Edge;
import com.practice.Graph.Graph;
import com.practice.Gui.Rib;
import com.practice.Gui.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.Collections;


public class Facade {
    private Graph graph = null;
    private BoruvkaAlg algorithm;
    private CareTaker careTaker;
    private Command command;
    private static final Logger logger = LogManager.getLogger(Facade.class);

    public Facade(){

    }
    /*public Facade(Algorithm algorithm){
        this.algorithm = (BoruvkaAlg) algorithm;
        this.careTaker = new CareTaker(this.algorithm);
    }*/

    public void initAlgorithm() throws NullPointerException{
        //создание алгоритма по существующему графу внутри фасада
        if(graph != null) {
            this.algorithm = new BoruvkaAlg(graph);
            this.careTaker = new CareTaker(algorithm);
        } else {
            logger.fatal("Невозможно инициализировать алгоритм, передан пустой граф");
            throw new NullPointerException();
        }
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
        careTaker.backup();
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

    /*public void loadGraphFromFile(String filename){
        this.graph = command.execute(filename);

    }*/

    public void createGraph(){
        this.graph = command.execute();
        graph.printEdges();
        graph.printVertices();
        //здесь вызывается одна из команд создания графа
    }

    public ArrayList<Edge> getMst() {
        return algorithm.getMst();
    }

    public void prev(){
        this.careTaker.undo();
    }

    public void next() {
        this.careTaker.stepNextMemento();
    }

    //Debug
    public void setGraph(Graph graph){
        this.graph = graph;
    }

    public void saveGraph(String filename){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String graphJson = gson.toJson(this.graph);
        //System.out.println(graphJson);
        try{
            if (!Files.exists(Path.of(filename))) {
                Path saveFile = Files.createFile(Path.of(filename));
                Files.write(saveFile, Collections.singleton(graphJson));
            } else {
                Files.write(Path.of(filename), Collections.singleton(graphJson));
            }
        } catch (IOException e){

        }
    }

    public void visualizeAlgorithm(Scene scene){
        scene.setRibs(algorithm.getMst());
    }

    public Graph getGraph(){
        return graph;
    }

}
