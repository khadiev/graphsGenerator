package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorConstants;
import lv.lu.bipartmatching.generators.GeneratorParams;
import lv.lu.bipartmatching.generators.GraphGenerator;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Kamil Khadiev on 30.12.2016.
 */
public abstract class GridGenerator extends GraphGenerator {
    private int gridSize;
    private List<Point> graph;

    protected List<Point> getGraph() {
        return graph;
    }

    protected void setGraph(List<Point> graph) {
        this.graph = graph;
    }

    protected int getGridSize() {
        return gridSize;
    }

    protected void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    @Override
    protected void outGraph(PrintWriter out) {
        List<Point> currentGraph = getGraph();
        int k = currentGraph.size();
//        long maxK = GeneratorConstants.getGridMaxPoints(getGridSize());
//        k = (int)Math.min(k, maxK);

        out.print(getGridSize() + " " + k);

        for (int i = 0; i < k; i++){
            Point point = currentGraph.get(i);
            out.println();
            out.print(point.getX() + " " + point.getY());
        }
    }

    @Override
    protected void relaxGraph(GeneratorParams params) {
        List<Point> g = getGraph();
        movePoints(g);
//        compressPoints(g, params);
        changeGridSize(g);
//        addPoint(g);
    }

    protected void addPoint(List<Point> g) {
        int n = getGridSize();
        n+=2;
        setGridSize(n);
        g.add(new Point(n-1, n-1));
    }


    protected void changeGridSize(List<Point> g) {
        int max = 0;
        for (Point p : g){
            max = (int)Math.max(max, p.getX());
            max = (int)Math.max(max, p.getY());
        }
        setGridSize(max+1);
//        int gsize = g.size()+1;
//        gsize = (int) Math.floor(gsize * (Math.log(gsize)+1))+1;
//        max = Math.max(max+1, gsize);
//        setGridSize(max);
    }

    protected void compressPoints(List<Point> g, GeneratorParams params) {
        GridGeneratorParams gridParams = (GridGeneratorParams) params;
        Set<Long> xs = new TreeSet<>();
        Set<Long> ys = new TreeSet<>();

        for (Point p : getGraph()){
            xs.add(p.getX());
            ys.add(p.getY());
        }

        List<Long> xsSorted = new ArrayList<>();
        List<Long> ysSorted = new ArrayList<>();
        xsSorted.addAll(xs);
        ysSorted.addAll(ys);


        int n = xs.size();

        long[] xa = new long[xs.size()];
        long[] ya = new long[xs.size()];



        for (int i = 1; i < n; i++){
            xa[i] = gridParams.getDistanceBetweenComponents() - (xsSorted.get(i) - xsSorted.get(i-1));
            ya[i] = gridParams.getDistanceBetweenComponents() - (ysSorted.get(i) - ysSorted.get(i-1));
        }
        for (int i = 1; i < n; i++){
            xa[i] += xa[i-1];
            ya[i] += ya[i-1];
        }

        for (Point p : getGraph()){
            long x = p.getX();
            long y = p.getY();

            int xi = Collections.binarySearch(xsSorted, x);
            int yi = Collections.binarySearch(ysSorted, y);

            p.setX(p.getX() + xa[xi]);
            p.setY(p.getY() + ya[yi]);
        }


    }

    protected void movePoints(List<Point> g) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Point p : g){
            minX = (int)Math.min(minX, p.getX());
            minY = (int)Math.min(minY, p.getY());
        }
        for (Point p : g){
            p.setX(p.getX() - minX);
            p.setY(p.getY() - minY);
        }
    }


}
