package com.practice.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateGraphDialog extends JFrame {
    // Текстовые поля
    JTextField countVerticesField, countEdgesField, minField, maxField;
    JButton okButton, cancelButton;
    private int countVertices, countEdges, min, max;

    public GenerateGraphDialog()
    {
        super("Параметры генерации графа");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Создание текстовых полей
        countVerticesField = new JTextField(15);
        countVerticesField.setToolTipText("Короткое поле");
        countEdgesField = new JTextField(15);
        countEdgesField.setToolTipText("Короткое поле");
        minField = new JTextField(15);
        minField.setToolTipText("Короткое поле");
        maxField = new JTextField(15);
        maxField.setToolTipText("Короткое поле");

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Создание панели с текстовыми полями
        Container container = getContentPane();
        container.setLayout(new GridLayout(5, 2));
        container.add(new JLabel("Введите количество вершин:"));
        container.add(countVerticesField);
        container.add(new JLabel("Введите количество ребер:"));
        container.add(countEdgesField);
        container.add(new JLabel("Введите минимальный вес ребра:"));
        container.add(minField);
        container.add(new JLabel("Введите максимальный вес ребра:"));
        container.add(maxField);
        container.add(okButton);
        container.add(cancelButton);

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        /*contents.add(new JLabel("Введите количество вершин:"));
        contents.add(countVerticesField);
        contents.add(new JLabel("Введите количество ребер:"));
        contents.add(countEdgesField);
        contents.add(new JLabel("Введите минимальный вес ребра:"));
        contents.add(minField);
        contents.add(new JLabel("Введите максимальный вес ребра:"));
        contents.add(maxField);
        contents.add(okButton);
        contents.add(cancelButton);*/

        // Определяем размер окна и выводим его на экран
        setSize(550, 200);
        setResizable(false);
        setVisible(true);
    }

    public int getCountVertices() {

        return countVertices;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    /*int getVerticesNum(){
        return Integer.parseInt(countVerticesField.getText());
    }

    int getEdgesNum(){
        return Integer.parseInt(countEdgesField.getText());
    }

    int getVerticesNum(){
        return Integer.parseInt(countVerticesField.getText());
    }*/
}
