import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("time.in"));
    int N = s.nextInt(); //cities
    int M = s.nextInt(); //roads
    int C = s.nextInt(); //cost
    
    int[] cost = new int[N];
    for (int i = 0; i < N; i++) {
      cost[i] = s.nextInt();
    }

    int[][] connections = new int[M][2];
    for (int i = 0; i < M; i++) {
      connections[i] = new int[]{s.nextInt() - 1, s.nextInt() - 1};
    }

    int a = 1000;
    //cities by, time
    int[][] dp = new int[N][a];
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < a; j++) {
        dp[i][j] = -1;
      }
    }
    dp[0][0] = 0;
    
    for (int i = 0; i < a - 1; i++) {
      for (int j = 0; j < M; j++) {
        if (dp[connections[j][0]][i] != -1) {
          dp[connections[j][1]][i + 1] = Math.max(dp[connections[j][1]][i + 1], cost[connections[j][1]] + dp[connections[j][0]][i]);
        }
      }  
    }
    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    */
    int ans = 0;
    for (int i = 0; i < a; i++) {
      ans = Math.max(ans, dp[0][i] - (i) * (i) * C);
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("time.out"))));
    pw.println(ans);
    pw.close();
  }
}