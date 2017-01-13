package lv.lu.bipartmatching.generators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.01.2017.
 */
public class BipartiteGraphEdgesList {
    private int n;
    private int m;
    private List<List<Edge>> edges = new ArrayList<>();
    private int[] ids = null;

    public BipartiteGraphEdgesList(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public BipartiteGraphEdgesList() {
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public List<List<Edge>> getEdges() {
        return edges;
    }

    public void setEdges(List<List<Edge>> edges) {
        this.edges = edges;
    }

    public List<Edge> getNeighbors(int i){

        while (edges.size()<=i){
            edges.add(new ArrayList<>());
        }
        return edges.get(i);
    }
    public void addEdge(int x, int y, int xId, int yId){
        addEdge(x, y, xId, yId, 1);
    }

    public void addEdge(int x, int y, int xId, int yId, int capacity){
        if (ids == null) ids = new int[n+m+2];
        x--;
        y--;
        y += n;
        ids[x] = xId;
        ids[y] = yId;

        Edge e1 = new Edge(x, y, capacity, 0);
        Edge e2 = new Edge(x, y, 0, 0);


        e1.setPairEdge(e2);
        e2.setPairEdge(e1);

        getNeighbors(x).add(e1);
        getNeighbors(y).add(e2);
    }

    public int getNM(){
        return getM() + getN();
    }

    public  int getVertexId(int v){
        return ids[v];
    }

    public static class Edge {
        private int a, b, capacity, flow;
        Edge pairEdge;

        public Edge(int a, int b, int capacity, int flow) {
            this.a = a;
            this.b = b;
            this.capacity = capacity;
            this.flow = flow;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getFlow() {
            return flow;
        }

        public void setFlow(int flow) {
            this.flow = flow;
        }

        public Edge getPairEdge() {
            return pairEdge;
        }

        public void setPairEdge(Edge pairEdge) {
            this.pairEdge = pairEdge;
        }
    }
}
