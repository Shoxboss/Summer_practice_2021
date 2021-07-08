package com.practice.Gui;

import com.practice.Graph.Node;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Scene extends JPanel {

    private boolean verticesAdding = false;
	private Rib rib;
	private Point from, to;
	private ArrayList<Rib> ribs;
	public Main.Option getCurrentOption() {
		return currentOption;
	}

	public void setCurrentOption( Main.Option currentOption ) {
		this.currentOption = currentOption;
	}

	Main.Option currentOption = Main.Option.NONE;


	Scene() {
		setOpaque( false );
        setBackground(new Color(240, 240, 240));
		setLayout( null );
		Font f = new Font("Monospaced", Font.BOLD, 20);
		setFont(f);
		ribs = new ArrayList<>();
		addMouseMotionListener( new MouseAdapter() {
			@Override
			public void mouseDragged( MouseEvent e ) {
				super.mouseDragged( e );
			}

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
				if(currentOption == Main.Option.CONNECT) {

					if( rib == null ) {
						to = from = vertex.getCenterPoint();
						rib = new Rib();
						rib.setSourceVertex( vertex );
						vertex.setColour( Color.cyan );
						validate();
						repaint();

					} else if( rib.isFull() == false ) {

						/*  there is some code that must be redo  */
						Integer weight = 0;
						String answer;

						for( int isNumber = 0; isNumber < 1;  ) {

							answer = JOptionPane.showInputDialog(null, "напишите число",
									"Вес ребра", JOptionPane.INFORMATION_MESSAGE);
							try {
								weight = Integer.parseInt(answer);
								isNumber = 1;
							}
							catch (NumberFormatException err)
							{
								continue;
							}
						}


						to = vertex.getCenterPoint();
						rib.setTargetVertex( vertex );
						rib.getSourceVertex().setColour( Color.lightGray );
						rib.setWeigth( weight );
						ribs.add( rib );
						rib = null;
						validate();
						repaint();
					}
				}
			}

			@Override
			public void mousePressed( MouseEvent e ) {
				super.mousePressed( e );
			}
		} );
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

	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D ) graphics;

		graphics2D.setStroke( new BasicStroke( 3 ) );
		graphics2D.setColor( Color.cyan );

		if(currentOption == Main.Option.CONNECT && rib != null) {
			graphics2D.drawLine( from.x, from.y, to.x, to.y );
		}
		for( Rib rib: ribs ) {
			graphics2D.setColor( Color.cyan );
			graphics2D.drawLine( rib.getLine()[0].x,rib.getLine()[0].y, rib.getLine()[1].x,rib.getLine()[1].y );

			graphics2D.setColor( Color.RED );
			graphics2D.drawString( ""+rib.getWeigth(), (rib.getLine()[0].x+rib.getLine()[1].x)/2, (rib.getLine()[0].y+rib.getLine()[1].y)/2 );

		}

	}
}
