package com.practice.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rib {

	private Vertex sourceVertex, targetVertex;
	private Integer weight;
	private Board component;
	
	public Rib() {
		sourceVertex = targetVertex = null;
		component = new Board("0", 0, 0);

		component.addMouseListener(new MouseAdapter(){
				
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2 ) {
					Integer weight = 0;
					String answer;
					String msg = "напишите вес ребра";

					int optionPane = JOptionPane.QUESTION_MESSAGE;

					for( int isNumber = 0; isNumber < 1;  ) {

						answer = JOptionPane.showInputDialog(null, msg,
								"Вес ребра", optionPane);
						if(answer == null) {
							return;
						}	
						try {
							weight = Integer.parseInt(answer);
							if( weight != 0 ) {
								isNumber = 1;
							}else {
								msg = "вес ребра должен быть целым числом больше нуля";
								optionPane = JOptionPane.WARNING_MESSAGE;
							}
						}
						catch (NumberFormatException err)
						{
							msg = "вес ребра должно быть целое натуральное число";
							optionPane = JOptionPane.ERROR_MESSAGE;
							continue;
						}
					}
					setWeight(weight);
				}
			}
		});

	}

	public Board getComponent() {
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
		int _x = (sourceVertex.getX() + targetVertex.getX())/2;
		int _y = (sourceVertex.getY() + targetVertex.getY())/2;
		component.setLocation( _x, _y );
	}

	public boolean isFull() {
		return sourceVertex != null && targetVertex != null;
	}

	public Integer getWeight() { return weight; }

	public void setWeight( Integer weight ) { 
	
		this.weight = weight;
		component.setName(weight.toString());
	}

	public void setNode( Vertex node ) {
		if( sourceVertex == null ) {

			sourceVertex = node;
		}
		else {
			targetVertex = node;
			int _x = (sourceVertex.getX() + targetVertex.getX())/2;
			int _y = (sourceVertex.getY() + targetVertex.getY())/2;
			component.setLocation( _x, _y );
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
