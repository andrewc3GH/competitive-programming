import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("taming.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    
    int[] costArray = new int[N];
    int[] endPoints = new int[N];
    for (int i = 0; i < N; i++) {
      int len = array[i] + 1;
      if (i - len + 1 < 0) {
        endPoints[i] = i;
      } else {
        endPoints[i] = i - len + 1;
      }
      if (len > i + 1) {
        costArray[i] = 0;
      } else {
        int val = len - 1;
        int ans = 0;
        for (int j = i; j > i - len; j--) {
          if (val == array[j]) {
            ans += 1;
          }
          val -= 1;
        }
        costArray[i] = len - ans;
      }
    }
    //System.out.println(Arrays.toString(costArray));
    //System.out.println(Arrays.toString(endPoints));

    int[][] intervals = new int[N][3]; //start, end, weight
    for (int i = 0; i < N; i++) {
      intervals[i] = new int[]{endPoints[i], i, costArray[i]};
    }
    Arrays.sort(intervals, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(intervals[i]));
    }

    //add all combinations of intervals

    int[][] dp = new int[N + 1][N]; //numbreakouts, index of last breakout (index N is inexistent)
    for (int i = 1; i < N + 1; i++) {
      for (int j = 0; j < N; j++) {
        dp[i][j] = N;
      }
    }

    for (int i = 0; i < N; i++) { //iterate through number of breakouts
      for (int j = 0; j < N; j++) { //iterate through last breakout
        int lastBreakout = j; //in terms of intervals array
        int endPoint = intervals[lastBreakout][1]; // <- fix here
        if (i == 0) {
          lastBreakout = -1;
          endPoint = N;
        }

        if (i == 0) {
          for (int k = 0; k < N; k++) {
            dp[i + 1][k] = Math.min(dp[i + 1][k], N - (intervals[k][1] - intervals[k][0] + 1) + intervals[k][2]);
          }
        } else {
          for (int k = 0; k < N; k++) { //go through every possible next breakout 
            //aligned with intervals (backwards)
            if (intervals[k][0] > endPoint) {
              dp[i + 1][k] = Math.min(dp[i + 1][k], dp[i][j] - (intervals[k][1] - intervals[k][0] + 1) + intervals[k][2]);
            }
          }
        }
      }
    }

    for (int a = 0; a < N + 1; a++){
      System.out.println(Arrays.toString(dp[a]));
    }
    

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("taming.out"))));
    pw.println();
    pw.close();
  }
}

/*

1 1 2 0 0 1
0 1 2 1 1 2

from each index, calculate how many before are in the right arrangement 

-> N intervals with weight (the amount saved with this interval)
2D dp array -> numBreakouts by last breakout

*/