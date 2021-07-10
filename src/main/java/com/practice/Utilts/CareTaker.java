package com.practice.Utilts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CareTaker {
    private BoruvkaAlg originator;
    private ArrayList<BoruvkaAlg.AlgorithmMemento> history;
    //private int cur = -1;
    //private int startIndex = 0;
    private int prevIndex = -2;
    private int nextIndex = 1;

    public CareTaker(BoruvkaAlg algorithm){
        originator = algorithm;
        history = new ArrayList<>();
    }

    public void backup(){
        history.add(originator.save());
        //cur++;
        prevIndex++;
    }

    public void stepNextMemento(){
        if (nextIndex >= 0 & nextIndex < history.size() && history.size() > 0){
            BoruvkaAlg.AlgorithmMemento snap = history.get(nextIndex);
            //
            prevIndex = nextIndex - 1;
            //
            nextIndex++;
            try {
                originator.restore(snap);
            } catch (Exception e){
                Logger logger = LogManager.getLogger(CareTaker.class);
                logger.error("Restore went wrong");
            }
        }
    }

    public void undo(){
        if (prevIndex >= 0 & prevIndex < history.size() & history.size() > 0) {
            BoruvkaAlg.AlgorithmMemento snap = history.get(prevIndex);
            nextIndex = prevIndex + 1;
            prevIndex--;
            try {
                originator.restore(snap);
            } catch (Exception e){
                Logger logger = LogManager.getLogger(CareTaker.class);
                logger.error("Restore went wrong");
            }
        }
    }
}
