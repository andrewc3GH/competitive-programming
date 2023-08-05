import java.io.*;
import java.util.*;

/*

shortest path algorithm on adj matrix
floyd-warshall's algo
n by m dp array
least number of days spent at [index][letter changing to]

*/

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cowmbat.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int K = s.nextInt();
    String str = s.next();

    int[] strArr = new int[N];
    for (int i = 0; i < N; i++) {
      strArr[i] = (int)str.charAt(i) - 97;
    }

    int[][] adjMatrix = new int[M][M];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < M; j++) {
        adjMatrix[i][j] = s.nextInt();
      }
    }
    
    for (int i = 0; i < M; i++) { //this is supposed to be the middle
      for (int j = 0; j < M; j++) {
        for (int k = 0; k < M; k++) {
          if (adjMatrix[i][k] > adjMatrix[i][j] + adjMatrix[j][k]) {
            adjMatrix[i][k] = adjMatrix[i][j] + adjMatrix[j][k];
          }
          if (adjMatrix[j][k] > adjMatrix[j][i] + adjMatrix[i][k]) { //this is the only necessary one
            adjMatrix[j][k] = adjMatrix[j][i] + adjMatrix[i][k];
          }
          if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
            adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
          }
          /*
          if (adjMatrix[k][i] > adjMatrix[k][j] + adjMatrix[j][i]) {
            adjMatrix[k][i] = adjMatrix[k][j] + adjMatrix[j][i];
          }
          if (adjMatrix[k][j] > adjMatrix[k][i] + adjMatrix[i][j]) {
            adjMatrix[k][j] = adjMatrix[k][i] + adjMatrix[i][j];
          }
          if (adjMatrix[j][i] > adjMatrix[j][k] + adjMatrix[k][i]) {
            adjMatrix[j][i] = adjMatrix[j][k] + adjMatrix[k][i];
          }
          */
        }
      }
    } 
    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(adjMatrix[i]));
    }
    
    System.out.println();
    */
    // N by M dp array
    int[][] dp = new int[N][M];
    int[][] other = new int[N][M];
    // currentIndex, currentLetter

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        other[i][j] = Integer.MAX_VALUE;
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (i > 0) {
          other[i][j] = other[i - 1][j] + adjMatrix[strArr[i]][j];
        } else {
          other[i][j] = adjMatrix[strArr[i]][j];
        }
      }
      //System.out.println(Arrays.toString(other[i]));
    }
    //System.out.println();
    for (int i = 0; i < N; i++) {
      //i = index
      for (int j = 0; j < M; j++) {
        //j = letter
        if (i > 0) {
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + adjMatrix[strArr[i]][j]);
        } else {
          dp[i][j] = Math.min(dp[i][j], adjMatrix[strArr[i]][j]);
        }
        
        if (i >= K + K - 1) {
          int val = other[i][j] - other[i - K][j];
          for (int k = 0; k < M; k++) {
            if (k != j) {
              dp[i][j] = Math.min(dp[i][j], dp[i - K][k] + val);
            }
          }
        }
      }
      //System.out.println(Arrays.toString(dp[i]));
    }
    int minVal = Integer.MAX_VALUE;
    for (int i = 0; i < M; i++) {
      minVal = Math.min(minVal, dp[N - 1][i]);
    }
    

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("cowmbat.out"))));
    pw.println(minVal);
    pw.close();
  }
}