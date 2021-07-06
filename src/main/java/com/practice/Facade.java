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


}
