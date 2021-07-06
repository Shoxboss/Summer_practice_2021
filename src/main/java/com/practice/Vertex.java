package com.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vertex extends JComponent implements MouseMotionListener, MouseListener {

	private Dimension size;
	public Color colour;
	private int x, y;
	private final int radius = 25;
	private String id;
	private volatile int draggedAtX, draggedAtY;

	protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

	public Vertex(  String id, int _x, int _y, Color colour ) {
		super();
		this.id = id;
		x = _x;
		y = _y;
		this.colour = colour;
		this.size = new Dimension(2*radius, 2*radius);
		setBounds( x-radius, y-radius, 2*radius, 2*radius );
		setPreferredSize(this.size);
		setBackground(colour);
		Font f = new Font("Monospaced", Font.BOLD, radius+10);
		setFont(f);
		setCursor( draggingCursor );
		System.out.println("new Vertex");
		addMouseListener( this );
		addMouseMotionListener( this );

	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent( g );
		Graphics2D g2 = ( Graphics2D ) g;

		g2.setColor( Color.lightGray );
		g2.fillOval( 0, 0, 2 * radius, 2 * radius );

		g2.setColor( Color.BLACK );
		FontMetrics fm = g2.getFontMetrics();
		int x = ( getWidth() - fm.stringWidth( id ) ) / 2;
		int y = (fm.getAscent() + getHeight())/2 ;
		g2.drawString( id, x, y );
		g2.drawString( id, 0, 0 );

	}

	private void print(String... argv) {
		
        Boolean flag = false;
        if( flag ) {

            for( String str: argv ) {
                System.out.println(str);
            }
        }
	}

	@Override
	public Dimension getPreferredSize() {
		return size;
	}

	@Override
	public void mouseDragged( MouseEvent mouseEvent ) {

		Point newLocation = new Point(mouseEvent.getX() - draggedAtX + getLocation().x, mouseEvent.getY() - draggedAtY + getLocation().y);
		setLocation(newLocation);
		print( "mouseDragged" );
	}


	@Override
	public void mouseMoved( MouseEvent mouseEvent ) {
//		print( "mouseMoved" );
	}

	@Override
	public void mouseClicked( MouseEvent mouseEvent ) {
		print( "mouseClicked" );
	}

	@Override
	public void mousePressed( MouseEvent mouseEvent ) {
		draggedAtX = mouseEvent.getX();
		draggedAtY = mouseEvent.getY();
	}

	@Override
	public void mouseReleased( MouseEvent mouseEvent ) {

	}

	@Override
	public void mouseEntered( MouseEvent mouseEvent ) {

	}

	@Override
	public void mouseExited( MouseEvent mouseEvent ) {

	}
}
