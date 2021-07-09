package com.practice.Gui;

import com.practice.Graph.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Scene extends JPanel {

	private Rib rib;
	private Point from, to;
	private ArrayList<Rib> ribs;
	private HashMap<String, Vertex> verticesDict;

	public ArrayList<Rib> getRibList(){
		return ribs;
	}

	public void addRib( Rib rib ) {
		ribs.add(rib);
		add(rib.getComponent());
		repaint();
	}

	Scene() {
		setOpaque( false );
        setBackground(new Color(240, 240, 240));
		setLayout( null );
		Font f = new Font("Monospaced", Font.BOLD, 20);
		setFont(f);
		ribs = new ArrayList<>();
		verticesDict = new HashMap<>();
		addMouseMotionListener( new MouseAdapter() {
			@Override
			public void mouseMoved( MouseEvent e ) {
				super.mouseMoved( e );
				to = e.getPoint();
				validate();
				repaint();
			}
		} );
	}


    public void addVertex( Vertex vertex ){

		vertex.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				super.mouseClicked( e );
				if(Main.currentOption == Main.Option.CONNECT) {

					if( rib == null ) {
						to = from = vertex.getCenterPoint();
						rib = new Rib();
						rib.setSourceVertex( vertex );
						vertex.setColour( Color.cyan );
						validate();
						repaint();

					} else if( rib.isFull() == false && rib.getSourceVertex() != vertex ) {

						Integer weight = 0;
						String answer;
						String msg = "напишите вес ребра";
				
						int optionPane = JOptionPane.QUESTION_MESSAGE;
				
						for( int isNumber = 0; isNumber < 1;  ) {
				
							answer = JOptionPane.showInputDialog(null, msg,
									"Вес ребра", optionPane);
							if(answer == null) {
								
								rib.getSourceVertex().setColour( Color.lightGray );
								rib = null;
								validate();
								repaint();
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
											
						to = vertex.getCenterPoint();
						rib.setTargetVertex( vertex );
						rib.getSourceVertex().setColour( Color.lightGray );
						rib.setWeight( weight );

						JComponent Jc = rib.getComponent();
						add( Jc );

						rib.getSourceVertex().addMouseListener( new MouseAdapter() {
							@Override
							public void mouseClicked( MouseEvent e ) {
								super.mouseClicked( e );
								if(Main.currentOption == Main.Option.DELETE) {
									remove( Jc );
								}							}
						} );
						vertex.addMouseListener( new MouseAdapter() {
							@Override
							public void mouseClicked( MouseEvent e ) {
								super.mouseClicked( e );
								if(Main.currentOption == Main.Option.DELETE) {
									remove( Jc );
								}							}
						} );

						Jc.addMouseListener(new MouseAdapter(){
						
							@Override
							public void mouseClicked(MouseEvent e) {

								if(Main.currentOption == Main.Option.DELETE) { 
									for(int i = 0; i < ribs.size(); i++) {
										if( ribs.get(i).isConnect( vertex ) ) {
											ribs.remove(i);
											break;
										}	
									}			
									remove(Jc);
									repaint();						
								}
							}
						});

						ribs.add( rib );
						rib = null;
						validate();
						repaint();
					}
				}
				else if( Main.currentOption == Main.Option.DELETE ) {
					
					
					for(int i = ribs.size()-1; i >= 0; i--) {
						if( ribs.get(i).isConnect( vertex ) ) {
							ribs.remove(i);
						}	
					}
					remove(vertex);      
					revalidate();
					repaint();
				}
			}
			@Override
			public void mousePressed( MouseEvent e ) {
				super.mousePressed( e );
			}
		} );
		verticesDict.put(vertex.getId(), vertex);
		add(vertex);
        validate();
        repaint();
    }

	public void clear() {
		removeAll();
		ribs.clear();
		revalidate();
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( getParent().getWidth()/2, getParent().getHeight());
	};

	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D ) graphics;

		graphics2D.setStroke( new BasicStroke( 3 ) );
		graphics2D.setColor( Color.cyan );

		if(Main.currentOption == Main.Option.CONNECT && rib != null) {
			graphics2D.drawLine( from.x, from.y, to.x, to.y );
		}
		for( Rib rib: ribs ) {

			graphics2D.setColor( Color.cyan );
			graphics2D.drawLine( rib.getLine()[0].x,rib.getLine()[0].y, rib.getLine()[1].x,rib.getLine()[1].y );
		}

	}

	public HashMap<String, Vertex> getVerticesDict(){
		return verticesDict;
	}
	public void setRibs(ArrayList<Edge> edges){
		ribs.clear();
		
		for (Edge edge : edges){
			Rib rib = new Rib();
			rib.setWeight(edge.getWeight());
			rib.setSourceVertex(verticesDict.get(edge.getStartName()));
			System.out.println(verticesDict.get(edge.getStartName()).getId());
			rib.setTargetVertex(verticesDict.get(edge.getEndName()));
			System.out.println(verticesDict.get(edge.getEndName()).getId());
			rib.setComponent(new Board(String.valueOf(edge.getWeight()), 0, 0));
			this.addRib(rib);
		}
	}

	public void removeRibs(){
		for(int i = ribs.size()-1; i >= 0; i--) {
			Board Jc = ribs.get(i).getComponent();
			remove(Jc);
			repaint();
		}
		ribs.clear();
		revalidate();
		repaint();
	}
}
