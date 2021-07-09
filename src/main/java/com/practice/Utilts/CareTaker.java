package com.practice.Utilts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CareTaker {
    private BoruvkaAlg originator;
    private ArrayList<BoruvkaAlg.AlgorithmMemento> history;
    private int cur = -1;
    private int startIndex = 0;

    public CareTaker(BoruvkaAlg algorithm){
        originator = algorithm;
        history = new ArrayList<>();
    }

    public void backup(){
        history.add(originator.save());
        cur++;
    }

    public void stepNextMemento(){
        if (startIndex >= 0 & startIndex < history.size() && history.size() > 0){
            BoruvkaAlg.AlgorithmMemento snap = history.get(startIndex);
            startIndex++;
            try {
                originator.restore(snap);
            } catch (Exception e){
                Logger logger = LogManager.getLogger(CareTaker.class);
                logger.error("Restore went wrong");
            }
        }
    }

    public void undo(){
        if (cur >= 0 & cur < history.size() & history.size() > 0) {
             BoruvkaAlg.AlgorithmMemento snap = history.get(cur);
             cur--;
             startIndex = cur;
             try {
                 originator.restore(snap);
             } catch (Exception e){
                 Logger logger = LogManager.getLogger(CareTaker.class);
                 logger.error("Restore went wrong");
             }
        }
    }
}
