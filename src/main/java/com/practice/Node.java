package com.practice;

public class Node implements Cloneable{

    private String name;
    private int component;
    private int x = 0;
    private int y = 0;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setComponent(int newComp) {
        component = newComp;
    }

    public int getComponent() {
        return component;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof Node) {
            if (this.getName().equals(((Node) o).getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public Object clone(){
        Node node = new Node(this.getName(), this.getX(), this.getY());
        node.setComponent(this.getComponent());
        return node;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }*/
}