package com.practice;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Scene extends JPanel implements MouseListener {

	private final String[] alphabet = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", " K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	private int counter;
    private boolean verticesAdding = false;

	Scene(Boolean flag) {
		setOpaque( false );
        if(flag) {
            addMouseListener( this );
        }
        setBackground(new Color(240, 240, 240));
		counter = 0;
		setLayout( null );
	}

    public void setVerticesAdding (boolean flag) {
        verticesAdding = flag;
    }


	@Override
	public void mouseClicked( MouseEvent mouseEvent ) {
        if(verticesAdding) {
            Vertex vertex = new Vertex(alphabet[counter++], mouseEvent.getX(), mouseEvent.getY(), Color.cyan  );
            add( vertex);
            validate();
            repaint();
        }
	}

	@Override
	public void mousePressed( MouseEvent mouseEvent ) {

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

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 500);
	};

// 	public static void main( String[] args ) {
// 		JFrame f = new JFrame();
// 		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
// //		Scene scene = new Scene();
// 		f.getContentPane().add( new Scene() );
// 		f.setSize( 600, 500 );
// 		f.setPreferredSize( new Dimension( 600, 500 ) );
// 		f.setLocation( 200, 200 );
// 		f.setVisible( true );
// 	}
}