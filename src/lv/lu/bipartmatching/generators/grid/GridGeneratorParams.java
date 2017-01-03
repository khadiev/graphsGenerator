package lv.lu.bipartmatching.generators.grid;

import lv.lu.bipartmatching.generators.GeneratorParams;

/**
 * Created by Kamil Khadiev on 30.12.2016.
 */
public class GridGeneratorParams extends GeneratorParams {
    private int maxn;
    private int minn;
    private int maxk;
    private int mink;
    private int distanceBetweenComponents;
    private int bridgeWidth;
    private int holesNumbers;
    private int sizeOfHole;


    public GridGeneratorParams(int maxn, int minn, int maxk, int mink, int distanceBetweenComponents) {
        this.maxn = maxn;
        this.minn = minn;
        this.maxk = maxk;
        this.mink = mink;
        this.distanceBetweenComponents = distanceBetweenComponents;
    }

    public GridGeneratorParams(int maxn, int minn, int maxk, int mink, int distanceBetweenComponents, int bridgeWidth) {
        this.maxn = maxn;
        this.minn = minn;
        this.maxk = maxk;
        this.mink = mink;
        this.distanceBetweenComponents = distanceBetweenComponents;
        this.bridgeWidth = bridgeWidth;
    }

    public GridGeneratorParams(int maxn, int minn, int maxk, int mink, int distanceBetweenComponents, int bridgeWidth, int holesNumbers, int sizeOfHole) {
        this.maxn = maxn;
        this.minn = minn;
        this.maxk = maxk;
        this.mink = mink;
        this.distanceBetweenComponents = distanceBetweenComponents;
        this.bridgeWidth = bridgeWidth;
        this.holesNumbers = holesNumbers;
        this.sizeOfHole = sizeOfHole;
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

    public int getDistanceBetweenComponents() {
        return distanceBetweenComponents;
    }

    public void setDistanceBetweenComponents(int distanceBetweenComponents) {
        this.distanceBetweenComponents = distanceBetweenComponents;
    }

    public int getBridgeWidth() {
        return bridgeWidth;
    }

    public void setBridgeWidth(int bridgeWidth) {
        this.bridgeWidth = bridgeWidth;
    }

    public int getHolesNumbers() {
        return holesNumbers;
    }

    public void setHolesNumbers(int holesNumbers) {
        this.holesNumbers = holesNumbers;
    }

    public int getSizeOfHole() {
        return sizeOfHole;
    }

    public void setSizeOfHole(int sizeOfHole) {
        this.sizeOfHole = sizeOfHole;
    }
}
