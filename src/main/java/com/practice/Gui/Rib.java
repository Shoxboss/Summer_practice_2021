package com.practice.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rib extends JComponent {

	private Vertex sourceVertex, targetVertex;
	private Integer weigth;

	public Rib() {
		sourceVertex = targetVertex = null;
	}

	public Vertex getSourceVertex() {
		return sourceVertex;
	}

	public Vertex getTargetVertex() {
		return targetVertex;
	}

	public void setSourceVertex( Vertex sourceVertex ) {
		this.sourceVertex = sourceVertex;
	}

	public void setTargetVertex( Vertex targetVertex ) {
		this.targetVertex = targetVertex;
	}

	public boolean isFull() {
		return sourceVertex != null && targetVertex != null;
	}

	public Integer getWeigth() { return weigth; }

	public void setWeigth( Integer weigth ) { this.weigth = weigth; }

	public void setNode( Vertex node ) {
		if( sourceVertex == null ) {

			sourceVertex = node;
		}
		else {
			targetVertex = node;
		}
	}

	public Point getCenterPoint( Vertex node ) {
		int  x = node.getX()+node.getWidth()/2;
		int  y = node.getY()+node.getHeight()/2;
		return new Point(x, y);
	}

	public Point[] getLine() {
		return new Point[]{ getCenterPoint( sourceVertex ), getCenterPoint( targetVertex ) };
	}

}
