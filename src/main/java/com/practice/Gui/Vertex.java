package com.practice.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vertex extends JComponent {

	private Dimension size;
	public Color colour;
	private int x, y;
	private final int radius = 25;
	private String id;
	private volatile int draggedAtX, draggedAtY;

	protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

	public Vertex(  String _id, int _x, int _y, Color _colour ) {
		super();
		id = _id; x = _x; y = _y; colour = _colour;
		size = new Dimension(2*radius, 2*radius);
		setBounds( x-radius, y-radius, 2*radius, 2*radius );
		setPreferredSize(size);
		Font f = new Font("Monospaced", Font.BOLD, radius);
		setFont(f);
		setCursor( draggingCursor );

		addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent mouseEvent ) {
				super.mousePressed( mouseEvent );

				draggedAtX = mouseEvent.getX();
				draggedAtY = mouseEvent.getY();
			}
		});


		addMouseMotionListener( new MouseAdapter() {
	
			@Override
			public void mouseDragged( MouseEvent mouseEvent ) {
				
				super.mouseDragged( mouseEvent );
				Point newLocation = new Point(mouseEvent.getX() - draggedAtX + getLocation().x, mouseEvent.getY() - draggedAtY + getLocation().y);
				setLocation(newLocation);
			}
		} );
	
	}

	public void setName(String answer) {
		id = answer;
		repaint();
	}

	public Point getCenterPoint( ) {
		int  x = getX()+getWidth()/2;
		int  y = getY()+getHeight()/2;
		return new Point(x, y);
	}

	public void setColour( Color colour ) {
		this.colour = colour;
		repaint();
	}

	public Color getColour() {
		return colour;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent( graphics );
		Graphics2D graphics2d= ( Graphics2D ) graphics;

		graphics2d.setColor( colour );
		graphics2d.fillOval( 2, 2, 2 * radius-3, 2 * radius-3);

		graphics2d.setColor(new Color( 0, 159, 153 ));
		FontMetrics fm = graphics2d.getFontMetrics();
		int x = ( getWidth() - fm.stringWidth( id ) ) / 2+2;
		int y = (fm.getAscent() + getHeight())/2-1 ;
		graphics2d.drawString( id, x, y );
	}

	@Override
	public Dimension getPreferredSize() {
		return size;
	}
}
