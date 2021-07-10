package com.practice.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateGraphDialog extends JFrame {
    // Текстовые поля
    JTextField countVerticesField, countEdgesField, minField, maxField;
    JButton okButton, cancelButton;

    public GenerateGraphDialog()
    {
        super("Параметры генерации графа");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        // Создание панели с текстовыми полями
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contents.add(new JLabel("Введите количество вершин:"));
        contents.add(countEdgesField);
        contents.add(new JLabel("Введите количество ребер:"));
        contents.add(countVerticesField);
        contents.add(new JLabel("Введите минимальный вес ребра:"));
        contents.add(minField);
        contents.add(new JLabel("Введите максимальный вес ребра:"));
        contents.add(maxField);
        contents.add(okButton);
        contents.add(cancelButton);
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран
        setSize(350, 250);
        setResizable(false);
        setVisible(true);
    }
}
