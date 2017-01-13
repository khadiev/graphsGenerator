package lv.lu.bipartmatching;

import lv.lu.bipartmatching.generators.GeneratorParams;
import lv.lu.bipartmatching.generators.GraphGenerator;
import lv.lu.bipartmatching.generators.grid.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<GraphGenerator> generators = null;
    private static List<GeneratorParams> params = null;

    public static void main(String[] args) throws IOException {
        System.out.println("Start generating...");
        long time = System.currentTimeMillis();
        initialization();

        int n = params.size();

        int startT = 0;
        int log_step_size = 10;

        for (int i = 0; i< n; i++) {
            int t = i;
            GraphGenerator generator = generators.get(i);
            GeneratorParams param = params.get(i);

            String tString = "" + (t + startT);
            while (tString.length() < 3) tString = "0" + tString;
            PrintWriter out = new PrintWriter("result/grids/grid" + tString + ".txt");


            generator.generateAndOut(out, param);
            out.close();
            if (i % log_step_size == 0){
                System.out.println("test " + i + " finished");
            }
        }
        System.out.println("test " + (n-1) + " finished");
        time = System.currentTimeMillis() - time;

        System.out.println("Running time: " + (1.0 * time / 1000));
        System.out.println("Finish generating...");
    }

    private static void initialization() {
        generators = new ArrayList<>();
        params = new ArrayList<>();
        addGenerator(new RandomGrid(), new GridGeneratorParams(10, 5, 30, 15, 5));
        for (int i = 0; i < 10; i++)
            addGenerator(new OneRectangleGrid(1), new GridGeneratorParams(10+(i*30), 5+(i*30), 30, 15, 5));
//        for (int i = 0; i < 3; i++) {
//            int maxn = 10+(i*60);
//            int minn = 5+(i*60);
//            int jStep = Math.max(1,minn/5/5);
//            int maxk = Math.max(maxn*maxn/10,1);
//            for (int j = 1; j < minn/5+1; j+=jStep)
//                addGenerator(new OneRaggedEdgesFigureGrid(), new GridGeneratorParams(maxn, minn, maxn*maxn/2, maxk, 5, j));
//        }
//
//        for (int i = 0; i < 3; i++) {
//            int maxn = 10+(i*60);
//            int minn = 5+(i*60);
//            int jStep = Math.max(1,minn/5/5);
//            int maxk = Math.max(maxn*maxn/10,1);
//            for (int j = 1; j < minn/5+1; j+=jStep) {
//                int maxh = Math.max(Math.min(5, maxk/10),1);
//                for (int h = 1; h <= maxh; h++) {
//                    int maxsh = Math.max(Math.min(3, maxk/70),1);
//                    for (int sh = 1; sh <= maxsh; sh++)
//                        addGenerator(new OneRaggedEdgesFigureWithHolesGrid(), new GridGeneratorParams(maxn, minn, maxn * maxn / 2, maxk, 5, j, h, sh));
//                }
//            }
//        }

    }

    private static void addGenerator(GraphGenerator generator, GeneratorParams param) {
        generators.add(generator);
        params.add(param);
    }


}
