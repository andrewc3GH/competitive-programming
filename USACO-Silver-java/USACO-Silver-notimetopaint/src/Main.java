import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int Q = s.nextInt();
    String str = s.next();

    int[][] intervals = new int[Q][2];
    for (int i = 0; i < Q; i++) {
        intervals[i] = new int[]{s.nextInt() - 1, s.nextInt() - 1};
    }

    HashMap<String, Integer> convert = new HashMap<>();
    convert.put("A", 0);
    convert.put("B", 1);
    convert.put("C", 2);
    convert.put("D", 3);
    convert.put("E", 4);
    convert.put("F", 5);
    convert.put("G", 6);
    convert.put("H", 7);
    convert.put("I", 8);
    convert.put("J", 9);
    convert.put("K", 10);
    convert.put("L", 11);
    convert.put("M", 12);
    convert.put("N", 13);
    convert.put("O", 14);
    convert.put("P", 15);
    convert.put("Q", 16);
    convert.put("R", 17);
    convert.put("S", 18);
    convert.put("T", 19);
    convert.put("U", 20);
    convert.put("V", 21);
    convert.put("W", 22);
    convert.put("X", 23);
    convert.put("Y", 24);
    convert.put("Z", 25);

    int[] array = new int[N];
    for (int i = 0; i < str.length(); i++) {
        array[i] = convert.get(Character.toString(str.charAt(i)));
    }
    //System.out.println(Arrays.toString(array));

    /*
    int[][] pSum = new int[N][26];
    pSum[0][array[0]] = 1;
    for (int i = 1; i < N; i++) {
        for (int j = 0; j < 26; j++) {
            pSum[i][j] = pSum[i - 1][j];
            if (array[i] == j) {
                pSum[i][j] += 1;
            }
        }
    }
    */

    int[] pSumNormal = new int[N];

    boolean[] paintLayers = new boolean[26];
    pSumNormal[0] = 1;
    paintLayers[array[0]] = true;
    for (int i = 1; i < N; i++) {
        int current = array[i];
        if (!paintLayers[current]) {
            pSumNormal[i] = pSumNormal[i - 1] + 1;
            paintLayers[current] = true;
        } else {
            pSumNormal[i] = pSumNormal[i - 1];
        }
        for (int j = current + 1; j < 26; j++) {
            paintLayers[j] = false;
        }
    }
    System.out.println(Arrays.toString(pSumNormal));

    int[] pSumOpposite = new int[N];

    paintLayers = new boolean[26];
    pSumOpposite[N - 1] = 1;
    paintLayers[array[N - 1]] = true;
    for (int i = N - 2; i > -1; i--) {
        int current = array[i];
        if (!paintLayers[current]) {
            pSumOpposite[i] = pSumOpposite[i + 1] + 1;
            paintLayers[current] = true;
        } else {
            pSumOpposite[i] = pSumOpposite[i + 1];
        }
        for (int j = current + 1; j < 26; j++) {
            paintLayers[j] = false;
        }
    }
    //System.out.println(Arrays.toString(pSumOpposite));

    for (int i = 0; i < Q; i++) {
        int current = 0;
        if (intervals[i][0] > 0) {
            current += pSumNormal[intervals[i][0] - 1];
        }
        if (intervals[i][1] < N - 1) {
            current += pSumOpposite[intervals[i][1] + 1];
        }
        System.out.println(current);
    }
    }
}
