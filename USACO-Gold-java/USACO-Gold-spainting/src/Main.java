import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    final int P = 1000000007;
    Scanner s = new Scanner(new File("spainting.in"));
    int N = s.nextInt();
    int M = s.nextInt(); //color
    int K = s.nextInt(); //brush len

    //index -> num squares painted
    long[] dp = new long[N + 1];
    
    dp[0] = 1;
    long val = 0;
    
    for (int i = 1; i < K; i++) {
        dp[i] = (dp[i - 1] * M) % P;
        val = (val + dp[i]) % P;
    }
    
    
    for (int i = K; i < N + 1; i++) {
        dp[i] = (dp[i] + (M-1) * val) % P;
        val = (val + dp[i] - dp[i - K + 1]) % P;
    }
    

    //every valid painting has at least one interval of len K that is the same color
    //find total combinations - combinations without one interval of len K
    
    long ans = 1;
    for (int i = 0; i < N; i++) {
        ans = (ans * M) % P;
    }
    
    //multiply last index by M and divide by M - 1
    //then subtract this from M^N
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("boards.out"))));
    pw.println((((ans - dp[N]) % P) + P) % P);
    pw.close();
  }
}