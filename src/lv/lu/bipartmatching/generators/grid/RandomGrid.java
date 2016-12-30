package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorConstants;
import lv.lu.bipartmatching.generators.GeneratorParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by acer on 30.12.2016.
 */
public class RandomGrid extends GridGenerator {
    @Override
    protected void generate(GeneratorParams params) {
        Random rnd = new Random(30122016L);
        GridGeneratorParams gridParams = (GridGeneratorParams) params;
        int n = rnd.nextInt(gridParams.getMaxn() - gridParams.getMinn()) + gridParams.getMinn();
        int maxk = gridParams.getMaxk();
        int mink = gridParams.getMink();

        int k = (maxk>mink ? rnd.nextInt(maxk - mink) : 0) + mink;

        Set<Point> points = new HashSet<>();

        while (points.size()<k){
            Point point = new Point(rnd.nextInt(n), rnd.nextInt(n));
            points.add(point);
        }

        setGraph(new ArrayList<>());
        getGraph().addAll(points);
    }
}
