import java.io.*;
import java.util.*;

//interval dp
//matrix multiplication problem

class Main {  
  public static void main(String[] args) throws IOException {

    //one index starting index
    //other index ending index
    
    Scanner s = new Scanner(new File("248.in"));
    int N = s.nextInt();

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    int otherMax = 0;

    //size, startingIndex
    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      dp[0][i] = array[i];
      otherMax = Math.max(array[i], otherMax);
    }

    //size of interval = i + 1
    for (int i = 1; i < N; i++) {
      //starting index = j
      for (int j = 0; j < N - i; j++) {
        int currentMax = 0;
        int left = 0;
        int right = i - 1;
        dp[i][j] = -1;
        //dividing index = k
        for (int k = j; k < j + i; k++) {
          //currentMax = Math.max(currentMax, dp[left][j]);
          //currentMax = Math.max(currentMax, dp[right][k + 1]);
          if (dp[left][j] == dp[right][k + 1] && dp[left][j] > 0) {
            currentMax = Math.max(currentMax, dp[left][j] + 1);
          }
          left += 1;
          right -= 1;
        }
        dp[i][j] = Math.max(dp[i][j], currentMax);
        otherMax = Math.max(otherMax, dp[i][j]);
      }
    }
    
    for (int i = 0; i < N; i++) {
      //System.out.println(Arrays.toString(dp[i]));
    }
    
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
    pw.println(Math.max(otherMax, dp[N - 1][0]));
    pw.close();
  }
}
