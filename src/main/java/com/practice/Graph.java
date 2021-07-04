import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> vertices;
    private ArrayList<Edge> edges;
    private int countVertices;
    private int countEdges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        countEdges = 0;
        countVertices = 0;
    }

    private int findVertex(String u) {
        if (vertices != null) {
            for (Node nodes : vertices) {
                if (nodes.getName().equals(u)) {
                    return vertices.indexOf(nodes);
                }
            }
        }
        return -1;
    }

    @Deprecated
    /*public void addVertex(String v) {
        if (!findVertex(v)) {
            vertices.add(new Node(v));
            countVertices++;
        }
    }*/

    public void addVertex(Node v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            countVertices++;
        }
    }

    @Deprecated
    /*public void addEdge(String u, String v, int w) {
        if (u.equals(v)) {
            return; // Дописать исключение IncorrectArgumentException
        }
        edges.add(new Edge(new Node(u), new Node(v), w));
        addVertex(u);
        addVertex(v);
        countEdges++;
    }*/

    public void addEdge(String u, String v, int w) {
        if (u.equals(v)) {
            return; // Дописать исключение IncorrectArgumentException
        }
        int indexStart = findVertex(u);
        int indexEnd = findVertex(v);
        Node start;
        Node end;
        if (indexStart != -1) {
            start = vertices.get(indexStart);
        }
        else {
            start = new Node(u);
            vertices.add(start);
            countVertices++;
        }
        if (indexEnd != -1) {
            end = vertices.get(indexEnd);
        }
        else {
            end = new Node(v);
            vertices.add(end);
            countVertices++;
        }
        edges.add(new Edge(start, end, w));
        countEdges++;
    }

    public void removeVertex(String v) {
        Node newNode = new Node(v);
        if (vertices.remove(newNode)) {
            for (Edge edge: edges) {
                if (edge.getStartName().equals(v) | edge.getEndName().equals(v)) {
                    removeEdge(edge.getStartName(), edge.getEndName());
                }
            }
        }
    }

    public void removeEdge(String start, String end ) {
        Edge newEdge = new Edge(new Node(start), new Node(end), 0);
        edges.remove(newEdge);
    }

    public ArrayList<Node> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getCountVertices() {
        return countVertices;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public void printVertices() {
        for (Node vertex: vertices) {
            System.out.println(vertex.getName() + " " + vertex.getComponent());
        }
    }

    public void printEdges() {
        for (Edge edge: edges) {
            System.out.println(edge.toString());
        }
    }

}