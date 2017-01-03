package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorParams;

import java.util.*;

/**
 * Created by Kamil Khadiev on 30.12.2016.
 */
public class OneRectangleGrid extends GridGenerator {
    @Override
    protected void generate(GeneratorParams params) {
        Random rnd = new Random(30122016L);
        GridGeneratorParams gridParams = (GridGeneratorParams) params;
        int n = rnd.nextInt(gridParams.getMaxn() - gridParams.getMinn()) + gridParams.getMinn();

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                points.add(new Point(i,j));

        setGraph(new ArrayList<>());
        getGraph().addAll(points);
    }
}
