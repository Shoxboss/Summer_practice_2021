package com.practice.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Rib {

	private Vertex sourceVertex, targetVertex;
	private Integer weight;
	private Board component;
	private Color[] colors = {
		Color.CYAN, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW
	};

	private Color color = Color.CYAN;
	
	public Rib() {
		sourceVertex = targetVertex = null;
		component = new Board("0", 0, 0);
		
		component.setParentComp(this);

		component.addMouseListener(new MouseAdapter(){
				
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2 && Main.isRedactorModeOn) {
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

	public Color getColor() {
		return color;
	}

	public void setColor(int index) {
		int i = index < colors.length ? index: index %  colors.length;
		this.color = colors[i];
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rib rib = (Rib) o;
		return (this.sourceVertex.getId().equals(rib.sourceVertex.getId()) && this.targetVertex.getId().equals(rib.targetVertex.getId()) && Objects.equals(weight, rib.weight)) |
				(this.sourceVertex.getId().equals(rib.targetVertex.getId()) && this.targetVertex.getId().equals(rib.sourceVertex.getId()) && Objects.equals(weight, rib.weight));
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceVertex.getId(), targetVertex.getId(), weight);
	}
}
