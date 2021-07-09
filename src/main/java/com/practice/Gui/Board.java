package com.practice.Gui;

import javax.swing.*;
import java.awt.*;

public class Board extends JComponent {

	private Dimension size;
	private String name;
	protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

	public Board(  String name, int x, int y) {
		super();
		this.name =name;
		size = new Dimension(30, 30);
		setBounds( x-10, y-10, 30, 30 );
		setBackground(Color.WHITE);
		setPreferredSize(size);
		Font f = new Font("Monospaced", Font.BOLD, 20);
		setFont(f);
		setCursor( draggingCursor );

	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent( graphics );
		Graphics2D graphics2d= ( Graphics2D ) graphics;

		FontMetrics fm = graphics2d.getFontMetrics();

		graphics2d.setColor( Color.BLACK );
		int lx = ( getWidth() - fm.stringWidth( name ) )/2;
		int ly = (fm.getAscent() + getHeight())/2 ;
		
		graphics2d.drawString( name, lx, ly );
	}
	@Override
	public Dimension getPreferredSize() {
		return size;
	}
}
