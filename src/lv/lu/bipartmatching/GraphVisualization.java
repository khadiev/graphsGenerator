package lv.lu.bipartmatching;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by user on 02.01.2017.
 */
public class GraphVisualization {
    public static void main(String[] args) throws IOException {
        int n = 204-24+1;
        int startT = 24;
        int log_step_size = 1;
        for (int i = 0; i <n; i++){
            int t = i;

            String tString = "" + (t + startT);
            while (tString.length() < 3) tString = "0" + tString;
            BufferedReader in = new BufferedReader(new FileReader("result/grids/grid" + tString + ".txt"));

            PrintWriter out = new PrintWriter("result/visual/grid" + tString + ".txt");

            printFile(in,out);
            out.close();
            in.close();
            if (i % log_step_size == 0){
                System.out.println("test " + i + " finished");
            }
        }
        System.out.println("test " + (n-1) + " finished");
    }

    private static void printFile(BufferedReader in, PrintWriter out) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine().trim());
        Integer n = Integer.parseInt(st.nextToken());
        Integer k = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][n];

        for (int i = 0; i <k; i++){
            st = new StringTokenizer(in.readLine().trim());
            Integer x = Integer.parseInt(st.nextToken());
            Integer y = Integer.parseInt(st.nextToken());
            a[x][y] = 1;
        }

        for (int i = 0; i<n; i++){

            for (int j = 0; j < n; j++){
                out.print(a[i][j]==1? "1" : ".");
            }
            out.println();
        }
    }
}
