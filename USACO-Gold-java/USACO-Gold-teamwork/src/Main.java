import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("teamwork.in"));
    int N = s.nextInt();
    int K = s.nextInt();

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    int[][][] dp = new int[N][K][2];
    //team value (current highest value), total value

    dp[0][0][0] = array[0];
    dp[0][0][1] = 0;

    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < K; j++) {
        if (dp[i][j][0] != 0 || dp[i][j][1] != 0) {
          if (j < K - 1) {
            dp[i + 1][j + 1][0] = Math.max(array[i + 1], dp[i][j][0]);
            dp[i + 1][j + 1][1] = dp[i][j][1];
          }
          int newVal = dp[i][j][1] + (j + 1) * dp[i][j][0];
          if (newVal > dp[i + 1][0][1]) {
            dp[i + 1][0][0] = array[i + 1];
            dp[i + 1][0][1] = newVal;
          }
          
        }
      }
    }
    //System.out.println();
    int maxVal = 0;
    for (int i = 0; i < K; i++) {
      dp[N - 1][i][1] += (i + 1) * dp[N - 1][i][0];
      maxVal = Math.max(maxVal, dp[N - 1][i][1]);
    }
    //System.out.println(maxVal);

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
    pw.println(maxVal);
    pw.close();
  }
}
