package com.practice;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Scene extends JPanel {// implements MouseListener {

    private boolean verticesAdding = false;

	Scene() {
		setOpaque( false );
        setBackground(new Color(240, 240, 240));
		setLayout( null );
	}

    public void addVertex( Vertex vertex ){
        add(vertex);
        validate();
        repaint();
    }

    public void setVerticesAdding (boolean flag) {
        verticesAdding = flag;
    }

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( getParent().getWidth()/2, getParent().getHeight());
	};
}
