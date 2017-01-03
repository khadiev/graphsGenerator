package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorParams;

import java.util.Random;

/**
 * Created by user on 03.01.2017.
 */
public class OneRaggedEdgesFigureWithHolesGrid extends OneRaggedEdgesFigureGrid {


    @Override
    protected void generate(GeneratorParams params) {
        super.generate(params);
        makeHoles(params);
        reBuildGraph();
    }

    private void makeHoles(GeneratorParams params) {
        makeGrid();
        Random rnd = new Random(03012017L);
        GridGeneratorParams params1 = (GridGeneratorParams) params;
        for (int i = 0; i < params1.getHolesNumbers(); i++){
            makeOneHole(params1.getSizeOfHole(), rnd);
        }
    }

    protected void makeOneHole(int sizeOfHole, Random rnd) {

        for (int i = 0; i< n; i++)
            for (int j = 0; j< n; j++)
                if (grid[i][j]>0) grid[i][j] = -1;

        int[][] grid1 = getCopy(grid);

        boolean isFirst = true;
        do {
            if (isFirst) {
                isFirst = false;
            } else {
                grid = getCopy(grid1);
            }
            Point p1 = getInsidePoint(rnd);
            Point p2 = getInsidePoint(rnd, p1);

            connectComponents(p1, p2, sizeOfHole, 0);
        } while (!isOneArea());


    }

    private boolean isOneArea() {
        int compId = Integer.MAX_VALUE/2;
        boolean isFirst = true;
        for (int i = 1; i < n; i++)
             for (int j = 1; j < m; j++){
                if (grid[i][j]==-1){
                    if (!isFirst) return false;
                    bfs_base(i, j, compId, -1);
                    isFirst = false;
                }
             }
        return true;
    }

    private int[][] getCopy(int[][] grid) {
        int[][] result =  new int[n+1][m+1];
        for (int i = 0; i < n+1; i++)
            for (int j = 0; j < m+1; j++)
                result[i][j] = grid[i][j];
        return result;
    }



    protected Point getInsidePoint(Random rnd) {
        return getInsidePoint(rnd, null);
    }

    protected Point getInsidePoint(Random rnd, Point p1) {
        Point p = new Point(rnd.nextInt(n-1)+1, rnd.nextInt(m-1)+1);
        while ((p1!= null && p.equals(p1)) || !isPointInside(p)){
            p = new Point(rnd.nextInt(n-1)+1, rnd.nextInt(m-1)+1);
        }
        return p;
    }

    protected boolean isPointInside(Point p) {
        for (int i = (int)p.getX()-1; i<= p.getX() + 1; i++)
            for (int j = (int)p.getY()-1; j<= p.getY() + 1; j++)
                if (grid[i][j]==0){
                    return false;
                }
        return true;
    }
}
