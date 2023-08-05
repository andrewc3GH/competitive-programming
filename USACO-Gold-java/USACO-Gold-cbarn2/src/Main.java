import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cbarn2.in"));
    int N = s.nextInt();
    int k = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    //intialize everything to 10 billion to be safe and convert everything to longs

    long answer = 1000000000 * 100;
    for (int start = 0; start < N; start++) {
      //number of doors used, location of most recent door
      long[][] dp = new long[k][N + 1];
      for (int i = 0; i < k; i++) {
        for (int j = 0; j < N + 1; j++) {
          dp[i][j] = 1000000000 * 100;
          if (i >= j) {
            dp[i][j] = 0;
          }
        }
      }
      int counter = 0;
      for (int i = 1; i < N + 1; i++) {
        dp[0][i] = dp[0][i - 1] + array[(i + start - 1)%N]*counter;
        counter += 1;
      }
      //System.out.println(Arrays.toString(dp[0]));
      for (int doorsUsed = 0; doorsUsed < k - 1; doorsUsed++) {
        for (int i = 0; i < N + 1; i++) {
          long temp = dp[doorsUsed][i];
          int passed = 0;
          for (int j = i + 1; j < N + 1; j++) {
            temp += array[(j + start - 1)%N]*passed;
            passed += 1;
            dp[doorsUsed + 1][j] = Math.min(temp, dp[doorsUsed + 1][j]);
            //System.out.println(temp + " " + dp[doorsUsed + 1][j]);
          }
        }
      }
      answer = Math.min(dp[k - 1][N], answer);
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
    pw.println(answer);
    pw.close();
  }
}
