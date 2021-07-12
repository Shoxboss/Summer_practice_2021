package com.practice.Utilts;
import java.awt.Point;
import java.util.Random;

import com.practice.Graph.Graph;
import com.practice.Graph.Node;
public class GenerateCommand implements Command{
    //Класс отвечает за генерацию случайного графа
    private int countVertices;
    private int countEdges;
    private int min;
    private int max;
    private int H = 500;
    private int W = 500;

    private final String[] alphabet = new String[]
            {
                    "A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N",
                    "O", "P", "Q", "R", "S", "T", "U",
                    "V", "W", "X", "Y", "Z"
            };

    public GenerateCommand(int countVertices, int countEdges, int min, int max){
        this.countVertices = countVertices;
        this.countEdges = countEdges;
        this.min = min;
        this.max = max;
    }

    @Override
    public Graph execute(){
        Graph graph = new Graph();

        for (int i = 0; i < countVertices; i++){
            graph.addVertex(alphabet[i]);
        }

        for (int i = 0; i < graph.getCountVertices(); i++){
   	Rand rand = new Rand();
            Node node = graph.getVertices().get(i);
            Point pos = rand.genPoint();
            node.setX(pos.x);
            node.setY(pos.y);


        }
        for (int i = 0; i < countEdges; i++){

            while (graph.getCountEdges() != (i + 1)){
                Rand rand = new Rand();
                int startInd = rand.randInt(0, countVertices-1);
                int endInd = rand.randInt(0, countVertices-1);
                int weight = rand.randInt(min, max);
                graph.addEdge(alphabet[startInd], alphabet[endInd], weight);
            }
        }
        return graph;
    }


    public void setBorders(int H, int W){
        this.H = H;
        this.W = W;
    }

    class Rand {
        int[][] massive;
        private int row = 15, col = 15;
        Rand() {
            massive = new int[row][col];
            for(int j=0;j< massive.length; j++){
                massive[j] = new int[col];
                for(int l = 0; l < massive.length; l++){
                    massive[j][l] = 0;
                }
            }
        }

        public int randInt(int min, int max){
            return (int)Math.floor(min + Math.random()*( Math.abs( max-min)));
        }

        public Point genPoint(){

            int x = randInt( 0, col ), y = randInt( 0, col );
            boolean flag = true;

            while(flag) {
                x = randInt( 1, col );
                y = randInt( 1, col );
                if( massive[x][y] != 0 ) {
                    continue;
                }
                massive[x][y] = 1;
                flag = false;
            }
            return  new Point( x*40, y*40 );
        }
    }
}
