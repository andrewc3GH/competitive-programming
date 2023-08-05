import java.io.*;
import java.util.*;

class Main {  
  public static int P;
  public static int MOD;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cownomics.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    P = 23;
    MOD = 1000000007;

    String[] arraySpotted = new String[N];
    for (int i = 0; i < N; i++) {
      arraySpotted[i] = s.next();
    }

    int[][] arrSpotted = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        int val = arraySpotted[i].charAt(j);
        arrSpotted[i][j] = val - 64;
      }
    }

    String[] arrayPlain = new String[N];
    for (int i = 0; i < N; i++) {
      arrayPlain[i] = s.next();
    }

    int[][] arrPlain = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        int val = arrayPlain[i].charAt(j);
        arrPlain[i][j] = val - 64;
      }
    }

    long[][] spottedHash = new long[N][M + 1];
    for (int i = 0; i < N; i++) {
      for (int j = 1; j < M + 1; j++) {
        spottedHash[i][j] = (spottedHash[i][j - 1] * (long)P + (long)arrSpotted[i][j - 1]) % MOD;
      }
    }
    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(spottedHash[i]));
    }
    */

    long[][] plainHash = new long[N][M + 1];
    for (int i = 0; i < N; i++) {
      for (int j = 1; j < M + 1; j++) {
        plainHash[i][j] = (plainHash[i][j - 1] * (long)P + (long)arrPlain[i][j - 1]) % MOD;
      }
    }
    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(plainHash[i]));
    }
    */

    long[] Pvals = new long[M + 1];
    Pvals[0] = 1;
    for (int i = 1; i < M + 1; i++) {
      Pvals[i] = ((long)Pvals[i - 1] * (long)P) % (long)MOD;
    }

    HashSet<Long> spottedSet = new HashSet<>();
    int ans = M + 1;
    
    int upperBound = 1;
    for (int i = 0; i < M - 1; i++) {
      boolean works = false;
      upperBound = Math.max(upperBound, i + 1);
      while (!works && upperBound < M + 1) {
        works = true;
        spottedSet = new HashSet<>();
        for (int k = 0; k < N; k++) {
          long newVal = MOD + (long)spottedHash[k][upperBound] - ((long)spottedHash[k][i] * (long)Pvals[upperBound - i]) % MOD;
          spottedSet.add(newVal % MOD);
        }
        HashSet<Long> plainSet = new HashSet<>();
        for (int k = 0; k < N; k++) {
          long otherVal = MOD + (long)plainHash[k][upperBound] - ((long)plainHash[k][i] * (long)Pvals[upperBound - i]) % MOD;
          plainSet.add(otherVal % MOD);
          if (spottedSet.contains(otherVal % MOD)) {
            works = false;
            upperBound += 1;
            break;
          }
        }
        //System.out.println(spottedSet + " " + plainSet);
      }
      
      if (works) {
        //System.out.println(i + " " + upperBound);
        ans = Math.min(ans, (upperBound - i));
      }
    }

    //System.out.println(ans);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
    pw.println(ans);
    pw.close();
    
  }
}