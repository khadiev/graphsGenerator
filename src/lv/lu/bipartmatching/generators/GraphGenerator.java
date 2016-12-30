package lv.lu.bipartmatching.generators;

import java.io.PrintWriter;

/**
 * Created by acer on 30.12.2016.
 */
public abstract class GraphGenerator {
    public void generateAndOut(PrintWriter out, GeneratorParams params){
        generate(params);
        outGraph(out);
    }

    protected abstract void outGraph(PrintWriter out);

    protected abstract void generate(GeneratorParams params);

}
