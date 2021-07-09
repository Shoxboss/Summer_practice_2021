package com.practice.Utilts;

import com.practice.Graph.Graph;
import com.practice.Gui.Rib;

import java.util.ArrayList;

public interface Command {
    Graph execute();
    /*Graph execute(ArrayList<Rib> ribs);
    Graph execute(String filename);*/
}
