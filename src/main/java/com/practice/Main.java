package com.practice;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main extends JFrame {
    private JPanel mainPanel;
    private Scene leftPanel;
    private Scene rightPanel;
    private JLabel statusLabel;
    private final String[] alphabet = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", " K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
    private int counter;

    private boolean verticesAdding = false;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        InitUI();
        this.pack();
        this.setVisible(true);

/* надо выносить в отделено метод

        Graph graph = new Graph();
        graph.addEdge("a", "b", 1);
        graph.addEdge("a", "c", 2);
        graph.addEdge("c", "b", 4);
        graph.addEdge("b", "d", 3);
        graph.addEdge("b", "e", 5);
        graph.addEdge("c", "e", 1);
        graph.addEdge("e", "d", 5);

        //BoruvkaAlg alg = new BoruvkaAlg(graph);
        Facade facade = new Facade(graph);
        CareTaker careTaker = new CareTaker(facade);

        //facade.doAlgorithm();
        //facade.printMst();

        facade.algorithmStep();
        careTaker.backup();
        facade.algorithmStep();

        facade.printMst();
        careTaker.undo();
        facade.printMst();
    */
    }

    private void InitUI() {

        // получить размер экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int W = (int)screenSize.getWidth(); // получить ширину экрана
        int H = (int)screenSize.getHeight(); // получить высоту экрана

        setMinimumSize(new Dimension(W/2, H/2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(createLeftPanel());
        mainPanel.add(createRightPanel());

        add(mainPanel);
        add(createToolBar(), BorderLayout.WEST);
        add(createStatusPanel(), BorderLayout.SOUTH);
        setJMenuBar(createMenuBar());
    }


    public void updateStatus(String msg) {
        String offset = "        ";
        statusLabel.setText(offset+msg);
    }
    private Scene createLeftPanel() {
        leftPanel = new Scene();
        leftPanel.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent mouseEvent ) {
                if(verticesAdding) {
                    Vertex vertex = new Vertex(alphabet[counter], mouseEvent.getX(), mouseEvent.getY(), Color.cyan );
                    leftPanel.addVertex( vertex );
                    rightPanel.addVertex( new Vertex(alphabet[counter++], mouseEvent.getX(), mouseEvent.getY(), Color.cyan ) );
                }
            }
        } );
        return leftPanel;
    }

    private Scene createRightPanel() {
        rightPanel = new Scene();
        rightPanel.setBackground(Color.white);
        rightPanel.setBorder(new LineBorder(new Color(232, 232, 232)));
        return rightPanel;
    }

    private JPanel createStatusPanel() {

        JPanel statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(getWidth(), 42));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        String offset = "        ";
        statusLabel = new JLabel(offset+"some comment");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        return statusPanel;
    }


    // создать меню файла
    private JMenu createFileMenu()
    {
        // Создание выпадающего меню
        JMenu fileMenu = new JMenu("Файл");

        // Пункт меню "Читать из файла"
        JMenuItem read = new JMenuItem("Читать из файла");

        // добавить действие Обработка
        read.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int option = fileChooser.showOpenDialog(getContentPane());
            if(option == JFileChooser.APPROVE_OPTION){
                File file1 = fileChooser.getSelectedFile();
                statusLabel.setText("Folder Selected: " + file1.getAbsolutePath() );
            }else{
                statusLabel.setText("Open command canceled");
            }
        });

        // Пункт меню "Сохранить"
        JMenuItem save = new JMenuItem("Сохранить");

        // добавить обработчик действия для кнопки "Сохранить"
        save.addActionListener( actionEvent -> {
            // ...
        });

        // Пункт меню "Сохранить как"
        JMenuItem save_as = new JMenuItem("Сохранить как");

        // добавить обработчик действия для кнопки "Сохранить как"
        save_as.addActionListener( actionEvent -> {

            String filename = JOptionPane.showInputDialog("Name this file");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(filename));
            fileChooser.showSaveDialog(fileChooser);
            BufferedWriter writer;
            int option = fileChooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                try {
                    writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                    writer.close();
                    JOptionPane.showMessageDialog(null, "File has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                statusLabel.setText("Open command canceled");
            }
        });


        // Пункт меню из команды с выходом из программы
        JMenuItem exit = new JMenuItem("Выход");

        // добавить обработчик действия для кнопки "Выход"
        exit.addActionListener( actionEvent -> {
            System.exit(0);
        });

        fileMenu.add(read);             // Добавление в меню пункт "Читать из файла"
        fileMenu.add(save);             // Добавление в меню пункт "Сохранить"
        fileMenu.add(save_as);          // Добавление в меню пункт "Сохранить как"
        fileMenu.addSeparator();        // Добавление разделителя
        fileMenu.add(exit);             // Добавление в меню пункт "Выход"

        return fileMenu;
    }

    // создать строку меню
    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createInfoMenu());

        return menuBar;
    }

    // создать информационное меню
    private JMenu createInfoMenu()
    {
        JMenu infoMenu = new JMenu("Информация");
        JMenuItem doc = new JMenuItem("Документация");
        JMenuItem aboutUs = new JMenuItem("О нас!");
        infoMenu.add(doc);

        doc.addActionListener(actionEvent -> {
            new Documentation();
        });

        infoMenu.add(aboutUs);

        aboutUs.addActionListener(actionEvent -> {
            new AboutUs();
        });
        return infoMenu;
    }

    // создать панель инструментов
    private JToolBar createToolBar()
    {
        JToolBar toolBar = new JToolBar(1);

        JButton addVertex_btn = new JButton(new ImageIcon("Images/vertex.png"));
        JButton addRib_btn = new JButton(new ImageIcon("Images/rib.png"));
        JButton delete_btn = new JButton(new ImageIcon("Images/delete.png"));
        JButton clear_btn = new JButton(new ImageIcon("Images/trash.png"));
        JButton back_btn = new JButton(new ImageIcon("Images/back.png"));
        JButton runStop_btn = new JButton(new ImageIcon("Images/run.png"));
        JButton forward_btn = new JButton(new ImageIcon("Images/forward.png"));

        addVertex_btn.addActionListener( actionEvent -> {
			
            verticesAdding = !verticesAdding; 
            leftPanel.setVerticesAdding(verticesAdding);
            if( verticesAdding ) {
                updateStatus( "Vertices Adding...");
                addVertex_btn.setBackground(new Color( 250, 100, 100 ));
            }else {
                addVertex_btn.setBackground(Color.WHITE);
            }
        });
        toolBar.add(addVertex_btn);
        toolBar.add(addRib_btn);
        toolBar.add(delete_btn);
        toolBar.add(clear_btn);
        toolBar.add(back_btn);
        toolBar.add(runStop_btn);
        toolBar.add(forward_btn);

        return toolBar;
    }
}
