package com.practice.Gui;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;

public class GenerateGraphDialog extends JDialog {

    private JFormattedTextField countVertex;
    private JFormattedTextField countRibs;
    private JFormattedTextField minWeight;
    private JFormattedTextField maxWeight;
    private JButton sendButton = new JButton("Генерировать");
    private Font font = new Font("Tahoma", Font.BOLD, 16);



    public Integer[] getAnswerArray() {
        
        Integer[] results = { 
                Integer.valueOf(countVertex.getText()), Integer.valueOf(countRibs.getText()),
                Integer.valueOf(minWeight.getText()), Integer.valueOf(maxWeight.getText())
            };
        return results;
    }
    public Integer getVerticesCount(){
        return Integer.valueOf(countVertex.getText());
    }
    
    public Integer getRibsCount(){
        return Integer.valueOf(countRibs.getText());
    }
    
    public Integer getMinWeight(){
        return Integer.valueOf(minWeight.getText());
    }
    
    public Integer getMaxWeight(){
        return Integer.valueOf(maxWeight.getText());
    }
    
    public JButton getButton() {
        return sendButton;
    }

    public GenerateGraphDialog() throws ParseException {
        int width = 208;
        int boxHeight = 24;
        int labelHeight = 20;
        int marging = 8;
        int Offset = 16;

        setResizable(false);
        setMinimumSize(new Dimension(2*Offset+width, 300));
        setMaximumSize(new Dimension(2*Offset+width, 300));
        setLayout(null);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormat formatVtx = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        NumberFormatter formatterVtx = new NumberFormatter(formatVtx);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatterVtx.setMaximum( 26 );
        formatterVtx.setMinimum( 1 );
        formatterVtx.setAllowsInvalid( true );
        formatter.setAllowsInvalid(true);

        JLabel lblVertex = new JLabel("Количество вершин:");
        lblVertex.setFont(font);
        lblVertex.setBounds(Offset, Offset, width, labelHeight);
        add(lblVertex);
        
        formatter.setMaximum(26);
        countVertex = new JFormattedTextField( formatterVtx );
        countVertex.setBounds(Offset, marging + Offset + labelHeight, width, boxHeight);
        add(countVertex);

        formatter.setMaximum(1000);

        
        JLabel lblRibs = new JLabel("Количество ребер:");
        lblRibs.setFont(font);
        lblRibs.setBounds(Offset, marging + 2*Offset + labelHeight+boxHeight, width, labelHeight);
        add(lblRibs);
        
        
        NumberFormatter formatterRib = new NumberFormatter(format);
        formatterRib.setValueClass(Integer.class);
        formatterRib.setMinimum(1);


        countRibs = new JFormattedTextField(formatterRib);
        countRibs.setBounds(Offset, 2*marging+2*Offset+2*labelHeight+boxHeight, width, boxHeight);
        add(countRibs);

        JLabel lblWeight = new JLabel("Диапазон веса:");
        lblWeight.setFont(font);
        lblWeight.setBounds(Offset,  2*marging + 3*Offset +2*labelHeight+2*boxHeight, width, labelHeight);
        add(lblWeight);

        JLabel lblMinWeight = new JLabel("Мин:");
        lblMinWeight.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMinWeight.setBounds(Offset,  3*marging + 3*Offset+3*labelHeight+2*boxHeight, 36, labelHeight);
        add(lblMinWeight);


        minWeight = new JFormattedTextField(formatter);
        minWeight.setBounds(Offset+42,  3*marging + 3*Offset+3*labelHeight+2*boxHeight, 48, boxHeight);
        add(minWeight);


        JLabel lblMaxWeight = new JLabel("Макс:");
        lblMaxWeight.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMaxWeight.setBounds(2*Offset+90, 3*marging + 3*Offset+3*labelHeight+2*boxHeight, 42, labelHeight);
        add(lblMaxWeight);


        maxWeight = new JFormattedTextField(formatter);
        maxWeight.setBounds(2*Offset+90+50, 3*marging + 3*Offset+3*labelHeight+2*boxHeight, 48, boxHeight);
        add(maxWeight);

        sendButton.setBounds(Offset, 3*marging+ 4*Offset+3*labelHeight+3*boxHeight, 208, 26);
        add(sendButton);
        
        addMouseMotionListener( new MouseAdapter() {
			@Override
			public void mouseMoved( MouseEvent e ) {
                sendButton.setEnabled( 
                    countVertex.getText().length() > 0  &&
                    countRibs.getText().length() > 0 &&
                    minWeight.getText().length() > 0 &&
                    maxWeight.getText().length() > 0
                );
			}
		} );

        setVisible(true);
    }



}
