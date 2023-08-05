import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("nocross.in"));
    int N = s.nextInt();
    int[] left = new int[N];
    for (int i = 0; i < N; i++) {
      left[i] = s.nextInt();
    }
    int[] right = new int[N];
    for (int i = 0; i < N; i++) {
      right[i] = s.nextInt();
    }

    int[][] dp = new int[N + 1][N + 1];

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        int val = 0;
        if (Math.abs(left[i - 1] - right[j - 1]) <= 4) {
          val = 1;
        }
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + val);
        dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
      }
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("nocross.out"))));
    pw.println(dp[N][N]);
    pw.close();
    
  }
}

/*

1 2 3 4 5 6
0 1 

6 5 4 3 2 1
0 1 


*/