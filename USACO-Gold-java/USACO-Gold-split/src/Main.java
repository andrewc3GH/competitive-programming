import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("split.in"));
    int N = s.nextInt();
    int[][] array = new int[N][2];

    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    //min x, min y, max x, max y
    int[] minMaxCoor = new int[]{array[0][0], array[0][1], array[0][0], array[0][1]};
    for (int i = 1; i < N; i++) {
      minMaxCoor[0] = Math.min(minMaxCoor[0], array[i][0]);
      minMaxCoor[1] = Math.min(minMaxCoor[1], array[i][1]);
      minMaxCoor[2] = Math.max(minMaxCoor[2], array[i][0]);
      minMaxCoor[3] = Math.max(minMaxCoor[3], array[i][1]);
    }

    long minSum = (long)(minMaxCoor[2] - minMaxCoor[0]) * (long)(minMaxCoor[3] - minMaxCoor[1]);
    long originalSum = minSum;

    //sort by x
    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    int[][] minMaxArray = new int[N][2];
    minMaxArray[0] = new int[]{array[0][1], array[0][1]};

    for (int i = 1; i < N; i++) {
      minMaxArray[i][0] = minMaxArray[i - 1][0];
      minMaxArray[i][1] = minMaxArray[i - 1][1];
      minMaxArray[i][0] = Math.min(minMaxArray[i][0], array[i][1]);
      minMaxArray[i][1] = Math.max(minMaxArray[i][1], array[i][1]);
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return b[0] - a[0];
    });

    int[][] minMaxArrayReversed = new int[N][2];
    minMaxArrayReversed[0] = new int[]{array[0][1], array[0][1]};

    for (int i = 1; i < N; i++) {
      minMaxArrayReversed[i][0] = minMaxArrayReversed[i - 1][0];
      minMaxArrayReversed[i][1] = minMaxArrayReversed[i - 1][1];
      minMaxArrayReversed[i][0] = Math.min(minMaxArrayReversed[i][0], array[i][1]);
      minMaxArrayReversed[i][1] = Math.max(minMaxArrayReversed[i][1], array[i][1]);
    }

    /*
    for (int[] a : minMaxArray) {
      System.out.println(Arrays.toString(a));
    }
    System.out.println();
    for (int[] a : minMaxArrayReversed) {
      System.out.println(Arrays.toString(a));
    }
    */

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    int minX = array[0][0];
    int maxX = array[N - 1][0];

    for (int i = 0; i < N - 1; i++) {
      if (array[i][0] != array[i + 1][0]) {
        long currentSum = (long)(array[i][0] - minX) * (long)(minMaxArray[i][1] - minMaxArray[i][0]) + (long)(maxX - array[i + 1][0]) * (long)(minMaxArrayReversed[N - i - 2][1] - minMaxArrayReversed[N - i - 2][0]);
        minSum = Math.min(minSum, currentSum);
      }
    }

    //System.out.println(originalSum + " - " + minSum);

    //sort by y
    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });

    minMaxArray = new int[N][2];
    minMaxArray[0] = new int[]{array[0][0], array[0][0]};

    for (int i = 1; i < N; i++) {
      minMaxArray[i][0] = minMaxArray[i - 1][0];
      minMaxArray[i][1] = minMaxArray[i - 1][1];
      minMaxArray[i][0] = Math.min(minMaxArray[i][0], array[i][0]);
      minMaxArray[i][1] = Math.max(minMaxArray[i][1], array[i][0]);
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return b[1] - a[1];
    });

    minMaxArrayReversed = new int[N][2];
    minMaxArrayReversed[0] = new int[]{array[0][0], array[0][0]};

    for (int i = 1; i < N; i++) {
      minMaxArrayReversed[i][0] = minMaxArrayReversed[i - 1][0];
      minMaxArrayReversed[i][1] = minMaxArrayReversed[i - 1][1];
      minMaxArrayReversed[i][0] = Math.min(minMaxArrayReversed[i][0], array[i][0]);
      minMaxArrayReversed[i][1] = Math.max(minMaxArrayReversed[i][1], array[i][0]);
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });

    int minY = array[0][1];
    int maxY = array[N - 1][1];

    for (int i = 0; i < N - 1; i++) {
      if (array[i][1] != array[i + 1][1]) {
        long currentSum = (long)(array[i][1] - minY) * (long)(minMaxArray[i][1] - minMaxArray[i][0]) + (long)(maxY - array[i + 1][1]) * (long)(minMaxArrayReversed[N - i - 2][1] - minMaxArrayReversed[N - i - 2][0]);
        minSum = Math.min(minSum, currentSum);
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
    pw.println(originalSum - minSum);
    pw.close();
    
  }
}