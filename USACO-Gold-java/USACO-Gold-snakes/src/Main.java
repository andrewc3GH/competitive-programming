import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("snakes.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    
    //index, numChanges
    int[][] dp = new int[N + 1][K + 1];
    
    //dp[1][0] = array[0];
    int maxVal = 0;

    for (int i = 1; i < N + 1; i++) {
      maxVal = Math.max(maxVal, array[i - 1]);
      dp[i][0] = maxVal * i;
      for (int j = 1; j < K + 1; j++) {
        //look left up
        dp[i][j] = dp[i - 1][j - 1] + array[i - 1];

        //look row
        int maxSwitch = array[i - 1];
        for (int k = i; k > 0; k--) {
          dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + maxSwitch * (i - k));
          maxSwitch = Math.max(maxSwitch, array[k - 1]);
        }
      }
    }
    int sum = 0;
    for (int i = 0; i < N; i++) {
      sum += array[i];
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("snakes.out"))));
    pw.println(dp[N][K] - sum);
    pw.close();
    
  }
}