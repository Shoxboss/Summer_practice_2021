package com.practice.Graph;

import java.util.Objects;

public class Edge implements Cloneable{

    private Node start;
    private Node end;
    private int weight;

    public Edge(Node start, Node end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setStartName(String start) {
        this.start.setName(start);
    }

    public String getStartName() {
        return start.getName();
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEndName(String end) {
        this.end.setName(end);
    }

    public String getEndName() {
        return end.getName();
    }

    @Override
    public String toString() {
        return getStartName() + "--" + getEndName() + " : " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof Edge) {
            if (((start.getName().equals(((Edge) o).getStartName()) &&
                    end.getName().equals(((Edge) o).getEndName())) ||
                    (start.getName().equals(((Edge) o).getEndName()) &&
                            end.getName().equals(((Edge) o).getStartName()))) &&
                    ((Edge)o).getWeight() == weight) {
                return true;
            }
        }
        return false;
    }

    /*@Override
    public int hashCode() {
        return start.hashCode() + end.hashCode();
    }*/

    @Override
    public int hashCode() {
        return start.hashCode() + end.hashCode() + weight;
    }

    @Override
    public Object clone(){
        Edge edge = new Edge((Node)this.getStart().clone(), (Node)this.getEnd().clone(), weight);
        return edge;
    }
}