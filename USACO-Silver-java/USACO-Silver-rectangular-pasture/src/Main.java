import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });
    for (int i = 0; i < N; i++) {
      array[i][0] = i;
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });
    for (int i = 0; i < N; i++) {
      array[i][1] = i;
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    int[][] twoDArray = new int[N][N];

    for (int[] a : array) {
      twoDArray[a[0]][a[1]] = 1;
    }

    int[][] horizontalSums = new int[N][N];

    for (int i = 0; i < N; i++) {
      int count = 0;
      for (int j = 0; j < N; j++) {
        if (twoDArray[i][j] == 1) {
          count += 1;
        }
        horizontalSums[i][j] = count;
      }
    }

    int[][] pSums = new int[N][N];

    for (int i = 0; i < N; i++) {
      int count = 0;
      for (int j = 0; j < N; j++) {
        if (horizontalSums[j][i] == 1) {
          count += 1;
        }
        pSums[j][i] = count;
      }
    }
    
    /*
    for (int[] a : twoDArray) {
      System.out.println(Arrays.toString(a));
    }
    
    
    
    for (int[] a : pSums) {
      System.out.println(Arrays.toString(a));
    }
    */
    

    //two separate nested loops -> prefix sums for rows, prefix sums for columns
    

    long add = 0;
    for (int i = 0; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        int[] xInterval = new int[]{Math.min(array[i][0], array[j][0]), Math.max(array[i][0], array[j][0])};
        int[] yInterval = new int[]{Math.min(array[i][1], array[j][1]), Math.max(array[i][1], array[j][1])};
        int numLeft = 1;
        /*
        for (int k = 0; k < i; k++) {
          if (array[k][1] > yInterval[0] && array[k][1] < yInterval[1]) {
            numLeft += 1;
          }
        }
        */
        if (xInterval[0] > 0 && yInterval[0] > 0) {
          numLeft += pSums[xInterval[0] - 1][yInterval[1]] - pSums[xInterval[0] - 1][yInterval[0] - 1];
        } else if (xInterval[0] > 0) {
          numLeft += pSums[xInterval[0] - 1][yInterval[1]];
        }
        int numRight = 1;
        /*
        for (int k = j + 1; k < N; k++) {
          if (array[k][1] > yInterval[0] && array[k][1] < yInterval[1]) {
            numRight += 1;
          }
        }*/
        if (xInterval[1] < N - 1 && yInterval[0] > 0) {
          numRight += pSums[N - 1][yInterval[1]] - pSums[xInterval[1]][yInterval[1]] - pSums[N - 1][yInterval[0] - 1] + pSums[xInterval[1]][yInterval[0] - 1];
        } else if (xInterval[1] < N - 1) {
          numRight += pSums[N - 1][yInterval[1]] - pSums[xInterval[1]][yInterval[1]];
          //System.out.println("below took alternate right");
        }
        add += numLeft*numRight;
        //System.out.println(i + " " + j + " -> " + numLeft + " " + numRight + " " + Arrays.toString(yInterval));
      }
    }
    add += N + 1;

    System.out.println(add);
  }
}