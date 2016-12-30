package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorConstants;
import lv.lu.bipartmatching.generators.GraphGenerator;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by acer on 30.12.2016.
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
        relaxGraph();
        List<Point> currentGraph = getGraph();
        int k = currentGraph.size();
        long maxK = GeneratorConstants.getGridMaxPoints(getGridSize());
        k = (int)Math.min(k, maxK);



        out.print(gridSize + " " + k);

        for (int i = 0; i < k; i++){
            Point point = currentGraph.get(i);
            out.println();
            out.print(point.getX() + " " + point.getY());
        }
    }

    private void relaxGraph() {
        List<Point> g = getGraph();
        movePoints(g);
        compressPoints(g);
        changeGridSize(g);
        addPoint(g);
    }

    private void addPoint(List<Point> g) {
        int n = getGridSize();
        n+=2;
        setGridSize(n);
        g.add(new Point(n-1, n-1));
    }


    private void changeGridSize(List<Point> g) {
        int max = 0;
        for (Point p : g){
            max = (int)Math.max(max, p.getX());
            max = (int)Math.max(max, p.getY());
        }
        max = Math.max(max, g.size()+1);
        setGridSize((int) Math.floor(max * (Math.log(max)+1))+1);
    }

    private void compressPoints(List<Point> g) {

    }

    private void movePoints(List<Point> g) {
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
