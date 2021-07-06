package com.practice;

import java.util.ArrayList;

public class CareTaker {
    private BoruvkaAlg originator;
    private ArrayList<BoruvkaAlg.AlgorithmMemento> history;
    private int cur = -1;

    public CareTaker(BoruvkaAlg algorithm){
        originator = algorithm;
        history = new ArrayList<>();
    }

    public void backup(){
        history.add(originator.save());
        cur++;
    }

    public void undo(){
        if (history.size() > 0) {
             BoruvkaAlg.AlgorithmMemento snap = history.get(cur);
             cur--;
             try {
                 originator.restore(snap);
             } catch (Exception e){
                 System.out.println("Restore went wrong");
             }
        }
    }
}
