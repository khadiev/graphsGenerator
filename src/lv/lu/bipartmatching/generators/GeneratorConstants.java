package lv.lu.bipartmatching.generators;

/**
 * Created by Kamil Khadiev on 30.12.2016.
 */
public final class GeneratorConstants {
    public static int getGridMaxPoints(int n){
        return n;
    }

    public static String atos(int[] a){
        StringBuilder sb = new StringBuilder();
        for (int x : a){
            sb.append(x + ", ");
        }
        return sb.toString();
    }
    public static String atos(long[] a){
        StringBuilder sb = new StringBuilder();
        for (long x : a){
            sb.append(x + ", ");
        }
        return sb.toString();
    }
}
