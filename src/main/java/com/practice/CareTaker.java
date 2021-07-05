package com.practice;

import java.util.ArrayList;
import java.util.Stack;

public class CareTaker {
    private Facade originator;
    private ArrayList<Memento> history;
    private int cur = -1;

    public CareTaker(Facade facade){
        originator = facade;
        history = new ArrayList<>();
    }

    public void backup(){
        history.add(originator.save());
        cur++;
    }

    public void undo(){
        if (history.size() > 0) {
             FacadeMemento snap = (FacadeMemento)history.get(cur);
             cur--;
             try {
                 originator.restore(snap);
             } catch (Exception e){
                 System.out.println("Restore went wrong");
             }
        }
    }
}
