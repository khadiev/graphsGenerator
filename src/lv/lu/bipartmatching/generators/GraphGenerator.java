package lv.lu.bipartmatching.generators;

import java.io.PrintWriter;

/**
 * Created by Kamil Khadiev on 30.12.2016.
 */
public abstract class GraphGenerator {
    public void generateAndOut(PrintWriter out, GeneratorParams params){
        generate(params);

        relaxGraph(params);
        outGraph(out);
    }

    protected abstract void outGraph(PrintWriter out);

    protected void relaxGraph(GeneratorParams params){

    }

    protected abstract void generate(GeneratorParams params);

}
