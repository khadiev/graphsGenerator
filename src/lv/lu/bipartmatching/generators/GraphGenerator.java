package lv.lu.bipartmatching.generators;

import lv.lu.bipartmatching.generators.grid.Point;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Created by Kamil Khadiev on 30.12.2016.
 */
public abstract class GraphGenerator {
    protected int havePerfectMatching = 0; //0 is "does not metter", 1 is "yes", 2 is "no"

    public GraphGenerator() {
        this(0);
    }

    public GraphGenerator(int havePerfectMatching) {
        this.havePerfectMatching = havePerfectMatching;
    }

    public void generateAndOut(PrintWriter out, GeneratorParams params){
        generate(params);
        relaxGraph(params);
        if (havePerfectMatching == 1) removeExcessVertexes();
        outGraph(out);
    }

    protected abstract void outGraph(PrintWriter out);

    protected void relaxGraph(GeneratorParams params){

    }

    protected abstract void generate(GeneratorParams params);

    protected abstract BipartiteGraphEdgesList getBipartiteGraphEdgesList();

    protected abstract void removeVertex(int id);

    private final int INF = Integer.MAX_VALUE / 2;



    protected void removeExcessVertexes() {
        edges = getBipartiteGraphEdgesList();

        for (int i = 0; i < edges.getN(); i++)
            edges.addEdge(edges.getNM() + 1, i-edges.getN()+1, -1, edges.getVertexId(i), 1);

        for (int i = edges.getN(); i < edges.getNM(); i++)
            edges.addEdge(i+1, edges.getNM()+1-edges.getN()+1, edges.getVertexId(i), -1, 1);

        s = edges.getNM();
        t = edges.getNM()+1;
        n = edges.getNM()+2;
        d = new int[n];
        ptr = new int[n];
        int flow = dinic();
        for (BipartiteGraphEdgesList.Edge e : edges.getNeighbors(edges.getNM())){
            if (e.getFlow()==0) {
                removeVertex(edges.getVertexId(e.getB()));
            }
        }
        for (BipartiteGraphEdgesList.Edge e : edges.getNeighbors(edges.getNM()+1)){
            if (e.getFlow()==0){
                removeVertex(edges.getVertexId(e.getA()));
            }
        }

    }

    private Queue<Integer> q = new ArrayDeque<>();
    private int[] d, ptr;
    private int s, t, n;
    private BipartiteGraphEdgesList edges;

    private boolean bfs() {
        q = new ArrayDeque<>();
        q.add(s);
        Arrays.fill(d, -1);
        d[s] = 0;
        while (!q.isEmpty() && d[t] == -1) {
            int v = q.poll();
            for (BipartiteGraphEdgesList.Edge e : edges.getNeighbors(v)) {
                int to = e.getB();
                if (d[to] == -1 && e.getFlow() < e.getCapacity()) {
                    q.add(to);
                    d[to] = d[v] + 1;
                }
            }
        }
        return d[t] != -1;
    }

    int dfs (int v, int flow) {
        if (flow == 0)  return 0;
        if (v == t)  return flow;
        int to;
        for (; ptr[v]< edges.getNeighbors(v).size(); ++ptr[v]) {
            BipartiteGraphEdgesList.Edge e = edges.getNeighbors(v).get(ptr[v]);
            to = e.getB();
            if (d[to] != d[v] + 1)  continue;
            int pushed = dfs (to, Math.min(flow, e.getCapacity() - e.getFlow()));
            if (pushed!=0) {
                e.setFlow(e.getFlow() +  pushed);
                e.getPairEdge().setFlow( e.getPairEdge().getFlow() - pushed);
                return pushed;
            }
        }
        return 0;
    }

    int dinic() {
        int flow = 0;
        int pushed;
        for (;;) {
            if (!bfs())  break;
            Arrays.fill(ptr, 0);
            pushed = dfs (s, INF);
            while (pushed>0){
                pushed = dfs (s, INF);
            }
            flow += pushed;
        }
        return flow;
    }

}
