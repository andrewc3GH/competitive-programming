import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("talent.in"));
    int N = s.nextInt();
    int W = s.nextInt();

    // weight, talent
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    //sort it and then do dp
    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    int[][] dp = new int[N][W];

    ArrayList<int[]> answers = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < W; j++) {
        dp[i][j] = -1;
      }
    }

    dp[0][0] = 0;
    if (array[0][0] < W) {
      dp[0][array[0][0]] = array[0][1];
    } else {
      answers.add(array[0]);
    }

    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    */

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < W; j++) {
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
        if (array[i][0] + j < W) {
          if (dp[i - 1][j] != -1) {
            dp[i][array[i][0] + j] = Math.max(dp[i - 1][j] + array[i][1], dp[i][array[i][0] + j]);
          }
        } else if (dp[i - 1][j] != -1) {
          answers.add(new int[]{array[i][0] + j, dp[i - 1][j] + array[i][1]});
        }
      }
    }

    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    */

    double maxVal = 0;
    for (int i = 0; i < answers.size(); i++) {
      maxVal = Math.max(maxVal, (double)answers.get(i)[1] / (double)answers.get(i)[0]);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
    pw.println((int)Math.floor(1000 * maxVal));
    pw.close();
  }
}
