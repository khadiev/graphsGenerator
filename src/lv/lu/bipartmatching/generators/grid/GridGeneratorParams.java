package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorParams;

/**
 * Created by acer on 30.12.2016.
 */
public class GridGeneratorParams extends GeneratorParams {
    private int maxn;
    private int minn;
    private int maxk;
    private int mink;

    public GridGeneratorParams(int maxn, int minn, int maxk, int mink) {
        this.maxn = maxn;
        this.minn = minn;
        this.maxk = maxk;
        this.mink = mink;
    }

    public int getMaxn() {
        return maxn;
    }

    public void setMaxn(int maxn) {
        this.maxn = maxn;
    }

    public int getMinn() {
        return minn;
    }

    public void setMinn(int minn) {
        this.minn = minn;
    }

    public int getMink() {
        return mink;
    }

    public void setMink(int mink) {
        this.mink = mink;
    }

    public int getMaxk() {
        return maxk;
    }

    public void setMaxk(int maxk) {
        this.maxk = maxk;
    }
}
