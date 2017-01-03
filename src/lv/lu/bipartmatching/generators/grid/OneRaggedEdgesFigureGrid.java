package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorParams;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by user on 02.01.2017.
 */
public class OneRaggedEdgesFigureGrid extends RandomGrid {
    int[][] grid;
    int[][] d;
    int n = 0;
    int m = 0;
    int x1,y1;
    int componentsNum = 0;
    int[] dx = new int[]{-1,+1, 0, 0};
    int[] dy = new int[]{ 0, 0,-1,+1};
    @Override
    protected void generate(GeneratorParams params) {
        super.generate(params);
        makeGrid();
        markComponents(params);
        removeHoles();
        reBuildGraph();
    }

    protected void removeHoles() {
        boolean first = true;
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                if (grid[i][j]==0){
                    if (first){
                       bfs_base(i,j,-1, 0);
                       first = false;
                    }
                    else{
                        grid[i][j] = 1;
                    }
                }
            }
        }
    }

    protected void reBuildGraph() {
        List<Point> g = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid[i][j]>0){
                    g.add(new Point(i-1,j-1));
                }
            }
        }
        setGraph(g);
    }

    protected void setGrid(int i, int j, int value){
        if (i>=0 && i<=n && j>=0 && j<=m)
            grid[i][j] = value;
    }

    protected void connectComponents(int x, int y, GeneratorParams params) {
        int z = ((GridGeneratorParams)params).getBridgeWidth();
        connectComponents(new Point(x1,y1), new Point(x,y), z, 1);

    }

    protected void connectComponents(Point p1, Point p2, int width, int value) {
        int xx1 = (int)p1.getX();
        int yy1 = (int)p1.getY();
        int xx2 = (int)p2.getX();
        int yy2 = (int)p2.getY();

        for (int q = 0; q< width; q++) {
            for (int i = xx1; i <= xx2; i++) {
                setGrid(i,q+yy1, value);
            }
        }

        for (int q = 0; q< width; q++) {
            int di = yy1<yy2 ? 1 : -1;
            for (int i = yy1; i != yy2; i+=di) {
                setGrid(xx2-q,i,value);
            }
            setGrid(xx2-q,yy2, value);
        }
    }


    protected void markComponents(GeneratorParams params) {
        int componentId = 0;

        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++){
                if (grid[i][j]==-1){
                    componentId++;
                    if (componentId==1){
                        x1 = i;
                        y1 = j;
                    }
                    bfs(i,j,componentId);
                    connectComponents(i, j, params);
                }
            }
        componentsNum = componentId;
    }

    protected void bfs(int i, int j, int componentId) {
        bfs_base(i,j, componentId, -1);
    }

    protected void bfs_base(int i, int j, int componentId, int empty_value) {
       bfs_base(i,j,componentId,empty_value,empty_value);

    }
    protected void bfs_base(int i, int j, int componentId, int empty_value_lower, int empty_value_upper) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(i,j));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            i = (int) p.getX();
            j = (int) p.getY();
            grid[i][j] = componentId;
            for (int q = 0; q < 4; q++) {
                if (i+dx[q]>=0 && i+dx[q]<=n && j+dy[q]>=0 && j+dy[q]<=m  )
                    if (grid[i + dx[q]][j + dy[q]] >= empty_value_lower && grid[i + dx[q]][j + dy[q]] <= empty_value_upper) {
                        grid[i + dx[q]][j + dy[q]] = componentId;
                        queue.add(new Point(i + dx[q], j + dy[q]));
                    }
            }
        }

    }

    protected void makeGrid() {
        movePoints(getGraph());

        for (Point p : getGraph()){
            n = (int)Math.max(n, p.getX());
            m = (int)Math.max(m, p.getY());
        }
        n+=2;
        m+=2;
        grid = new int[n+1][m+1];
        for (Point p : getGraph()){
            grid[(int)p.getX()+1][(int)p.getY()+1] = -1;
        }
    }
}
