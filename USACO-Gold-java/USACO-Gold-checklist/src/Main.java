import java.io.*;
import java.util.*;

class Main {  
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
    //x, y, distance
    int[][][] dp = new int[H + 1][G + 1][2];
    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        dp[i][j][0] = 1000000000;
        dp[i][j][1] = 1000000000;
      }
    }

    //start on G
    //dp[0][1][1] = new int[]{Garray[0][0], Garray[0][1], 0};
    //start on H
    dp[1][0][0] = 0;

    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        for (int k = 0; k < 2; k++) {
          //take H
          if (i < H) {
            if (k == 0) {
              dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], dp[i][j][k] + (Math.abs(Harray[i + 1][0] - Harray[i][0]) + Math.abs(Harray[i + 1][1] - Harray[i][1])) * (Math.abs(Harray[i + 1][0] - Harray[i][0]) + Math.abs(Harray[i + 1][1] - Harray[i][1])));
            } else {
              dp[i + 1][j][k - 1] = Math.min(dp[i + 1][j][k - 1], dp[i][j][k] + (Math.abs(Harray[i + 1][0] - Garray[j][0]) + Math.abs(Harray[i + 1][1] - Garray[j][1])) * (Math.abs(Harray[i + 1][0] - Garray[j][0]) + Math.abs(Harray[i + 1][1] - Garray[j][1])));
            }
          }
          //take G
          if (j < G) {
            if (k == 1) {
              dp[i][j + 1][k] = Math.min(dp[i][j + 1][k], dp[i][j][k] + (Math.abs(Garray[j + 1][0] - Garray[j][0]) + Math.abs(Garray[j + 1][1] - Garray[j][1])) * (Math.abs(Garray[j + 1][0] - Garray[j][0]) + Math.abs(Garray[j + 1][1] - Garray[j][1])));
            } else {
              System.out.println(i + " " + j + " ");
              dp[i][j + 1][k + 1] = Math.min(dp[i][j + 1][k + 1], dp[i][j][k] + (Math.abs(Garray[j + 1][0] - Harray[i][0]) + Math.abs(Garray[j + 1][1] - Harray[i][1])) * (Math.abs(Garray[j + 1][0] - Harray[i][0]) + Math.abs(Garray[j + 1][1] - Harray[i][1])));
            }
          }
        }
      }
    }

    /*
    //h index, g index, x, y, distance
    int[][][] dp = new int[H + 1][G + 1][5];
    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        dp[i][j] = new int[]{-1, -1, -1, -1, -1};
      }
    }
    dp[0][1] = new int[]{0, 1, Garray[0][0], Garray[0][1], 0};
    dp[1][0] = new int[]{1, 0, Harray[0][0], Harray[0][1], 0};

    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        if (dp[i][j][0] != -1 || dp[i][j][1] != -1) {
          //take H
          if (i < H) {
            int[] oldArr = dp[i + 1][j];
            dp[i + 1][j][0] = dp[i][j][0] + 1;
            dp[i + 1][j][1] = dp[i][j][1];
            dp[i + 1][j][2] = Harray[dp[i][j][0]][0];
            dp[i + 1][j][3] = Harray[dp[i][j][0]][1];
            //dp[i + 1][j][4] = dp[i][j][4] + (dp[i + 1][j][2] - dp[i][j][2]) * (dp[i + 1][j][2] - dp[i][j][2]) + (dp[i + 1][j][3] - dp[i][j][3]) * (dp[i + 1][j][3] - dp[i][j][3]);
            dp[i + 1][j][4] = dp[i][j][4] + (Math.abs(dp[i + 1][j][2] - dp[i][j][2]) + Math.abs(dp[i + 1][j][3] - dp[i][j][3])) * (Math.abs(dp[i + 1][j][2] - dp[i][j][2]) + Math.abs(dp[i + 1][j][3] - dp[i][j][3]));
            if (oldArr[4] < dp[i + 1][j][4] && (oldArr[0] != -1 || oldArr[1] != -1)) {
              dp[i + 1][j] = oldArr;
            }
          }
          
          //take G
          if (j < G) {
            int[] oldArr = dp[i][j + 1];
            dp[i][j + 1][0] = dp[i][j][0];
            dp[i][j + 1][1] = dp[i][j][1] + 1;
            dp[i][j + 1][2] = Garray[dp[i][j][1]][0];
            dp[i][j + 1][3] = Garray[dp[i][j][1]][1];
            //dp[i][j + 1][4] = dp[i][j][4] + (dp[i][j + 1][2] - dp[i][j][2]) * (dp[i][j + 1][2] - dp[i][j][2]) + (dp[i][j + 1][3] - dp[i][j][3]) * (dp[i][j + 1][3] - dp[i][j][3]);
            dp[i][j + 1][4] = dp[i][j][4] + (Math.abs(dp[i][j + 1][2] - dp[i][j][2]) + Math.abs(dp[i][j + 1][3] - dp[i][j][3])) * (Math.abs(dp[i][j + 1][2] - dp[i][j][2]) + Math.abs(dp[i][j + 1][3] - dp[i][j][3]));
            if (oldArr[4] < dp[i][j + 1][4] && (oldArr[0] != -1 || oldArr[1] != -1)) {
              dp[i][j + 1] = oldArr;
            }
          }
        }
      }
    }
    */
    /*
    for (int i = 0; i < H + 1; i++) {
      for (int j = 0; j < G + 1; j++) {
        System.out.println(i + " " + j + " " + Arrays.toString(dp[i][j][0]) + " " + Arrays.toString(dp[i][j][1]));
      }
    }
    */

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
    pw.println(dp[H][G][0]);
    pw.close();
    
  }
}
