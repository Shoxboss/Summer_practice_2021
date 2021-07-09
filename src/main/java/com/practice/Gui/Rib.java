package com.practice.Gui;

import java.awt.*;

import javax.swing.JComponent;

public class Rib {

	private Vertex sourceVertex, targetVertex;
	private Integer weigth;
	private Board component;
	
	public Rib() {
		sourceVertex = targetVertex = null;
	}

	public Board getComponent() {

		if(component == null) {
			component = new Board(weigth.toString(),
			(sourceVertex.getX() + targetVertex.getX())/2, 
			(sourceVertex.getY() + targetVertex.getY())/2);
		}
		return component;
	}

	public void setComponent(Board component) {
		this.component = component;
	}

	public boolean isConnect(Vertex vertex) {

		return sourceVertex == vertex || targetVertex == vertex;
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

	public void setWeigth( Integer weigth ) { 
		
		this.weigth = weigth;
	}

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
		component.setLocation(	(sourceVertex.getX() + targetVertex.getX())/2, 
								(sourceVertex.getY() + targetVertex.getY())/2 );
		return new Point[]{ getCenterPoint( sourceVertex ), getCenterPoint( targetVertex ) };
	}
}
