import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("radio.in"));
    int N = s.nextInt();
    int M = s.nextInt();

    int[][] farmerLocation = new int[N + 1][2];
    int[][] bessieLocation = new int[M + 1][2];
    farmerLocation[0] = new int[]{s.nextInt(), s.nextInt()};
    bessieLocation[0] = new int[]{s.nextInt(), s.nextInt()};

    String farmer = s.next();
    for (int i = 1 ; i < N + 1; i++) {
      farmerLocation[i] = farmerLocation[i - 1].clone();
      if (Character.toString(farmer.charAt(i - 1)).equals("N")) {
        farmerLocation[i][1] += 1;
      }
      if (Character.toString(farmer.charAt(i - 1)).equals("S")) {
        farmerLocation[i][1] -= 1;
      }
      if (Character.toString(farmer.charAt(i - 1)).equals("E")) {
        farmerLocation[i][0] += 1;
      }
      if (Character.toString(farmer.charAt(i - 1)).equals("W")) {
        farmerLocation[i][0] -= 1;
      }
    }
    
    String bessie = s.next();
    for (int i = 1 ; i < M + 1; i++) {
      bessieLocation[i] = bessieLocation[i - 1].clone();
      if (Character.toString(bessie.charAt(i - 1)).equals("N")) {
        bessieLocation[i][1] += 1;
      }
      if (Character.toString(bessie.charAt(i - 1)).equals("S")) {
        bessieLocation[i][1] -= 1;
      }
      if (Character.toString(bessie.charAt(i - 1)).equals("E")) {
        bessieLocation[i][0] += 1;
      }
      if (Character.toString(bessie.charAt(i - 1)).equals("W")) {
        bessieLocation[i][0] -= 1;
      }
    }

    int[][] dp = new int[N + 1][M + 1];
    for (int i = 0; i < N + 1; i++) {
      for (int j = 0; j < M + 1; j++) {
        dp[i][j] = 2000000000;
      }
    }
    dp[0][0] = 0;

    for (int i = 0; i < N + 1; i++) {
      for (int j = 0; j < M + 1; j++) {
        if (i < N) {
          //take only FJ
          dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + (int)Math.pow(farmerLocation[i + 1][0] - bessieLocation[j][0], 2) + (int)Math.pow(farmerLocation[i + 1][1] - bessieLocation[j][1], 2));
        }
        if (j < M) {
          //take only Bessie
          dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + (int)Math.pow(farmerLocation[i][0] - bessieLocation[j + 1][0], 2) + (int)Math.pow(farmerLocation[i][1] - bessieLocation[j + 1][1], 2));
        }
        if (i < N && j < M) {
          //take both
          dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + (int)Math.pow(farmerLocation[i + 1][0] - bessieLocation[j + 1][0], 2) + (int)Math.pow(farmerLocation[i + 1][1] - bessieLocation[j + 1][1], 2));
        }
      }
    }
    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(dp[N]));
    }
    */
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("radio.out"))));
    pw.println(dp[N][M]);
    pw.close();
  }
}
