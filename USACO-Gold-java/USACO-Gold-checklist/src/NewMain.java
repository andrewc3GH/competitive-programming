import java.io.*;
import java.util.*;

class NewMain {  
  
  //write a helper function to calculate the distance between two cows
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("checklist.in"));
    int H = s.nextInt();
    int G = s.nextInt();

    int[][] Harray = new int[H][2];
    int[][] Garray = new int[G][2];

    //1 0, 1 1, 1 2, 2 2, 3 2 = 20
    //0 1, 0 2, 1 2, 2 2, 3 2 = 19
    for (int i = 0; i < H; i++) {
      Harray[i] = new int[]{s.nextInt(), s.nextInt()};
    }
    for (int i = 0; i < G; i++) {
      Garray[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    //currently on H, currently on G
    int[][][] dp = new int[H + 1][G + 1][2];
    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        dp[i][j][0] = 2000000000;
        dp[i][j][1] = 2000000000;
      }
    }

    //start on H
    dp[1][0][0] = 0;

    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        for (int k = 0; k < 2; k++) {
          //take H
          if (i < H) {
            if (k == 0 && i > 0) {
              dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], dp[i][j][k] + (Harray[i][0] - Harray[i - 1][0])*(Harray[i][0] - Harray[i - 1][0]) + (Harray[i][1] - Harray[i - 1][1])*(Harray[i][1] - Harray[i - 1][1]));
            } else if (k == 1 && j > 0) {
              dp[i + 1][j][k - 1] = Math.min(dp[i + 1][j][k - 1], dp[i][j][k] + (Harray[i][0] - Garray[j - 1][0])*(Harray[i][0] - Garray[j - 1][0]) + (Harray[i][1] - Garray[j - 1][1])*(Harray[i][1] - Garray[j - 1][1]));
            }
          }
          //take G
          if (j < G) {
            if (k == 1 && j > 0) {
              dp[i][j + 1][k] = Math.min(dp[i][j + 1][k], dp[i][j][k] + (Garray[j][0] - Garray[j - 1][0])*(Garray[j][0] - Garray[j - 1][0]) + (Garray[j][1] - Garray[j - 1][1])*(Garray[j][1] - Garray[j - 1][1]));
            } else if (k == 0 && i > 0) {
              dp[i][j + 1][k + 1] = Math.min(dp[i][j + 1][k + 1], dp[i][j][k] + (Garray[j][0] - Harray[i - 1][0])*(Garray[j][0] - Harray[i - 1][0]) + (Garray[j][1] - Harray[i - 1][1])*(Garray[j][1] - Harray[i - 1][1]));
            }
          }
        }
      }
    }
    /*
    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        System.out.println(i + " " + j + " " + Arrays.toString(dp[i][j]));
      }
    }
    */

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
    pw.println(dp[H][G][0]);
    pw.close();
    
  }
}
