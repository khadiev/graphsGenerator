package lv.lu.bipartmatching;

import lv.lu.bipartmatching.generators.GraphGenerator;
import lv.lu.bipartmatching.generators.grid.GridGeneratorParams;
import lv.lu.bipartmatching.generators.grid.RandomGrid;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        GraphGenerator generator = new RandomGrid();
        int startT = 0;

        int t = 1;
        String tString = "" + (t + startT);
        while (tString.length()<3) tString = "0" + tString;
        PrintWriter out = new PrintWriter("result/grids/grid" + tString + ".txt");

        GridGeneratorParams params = new GridGeneratorParams(10, 5, 30, 15);
        generator.generateAndOut(out, params);
        out.close();

    }
}
