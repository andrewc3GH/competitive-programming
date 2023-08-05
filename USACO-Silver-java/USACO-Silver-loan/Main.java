import java.io.*;
import java.util.*;

class Main {
  public static long N;
  public static long K;
  public static long M;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("loan.in"));
    N = s.nextLong();
    K = s.nextLong();
    M = s.nextLong();
    s.close();
    /*int numRemaining = N - ((K - 1) * M);*/
    long minRange = 1;
    long maxRange = K;
    long currentNum = 0;
    while (true) {
      currentNum = findIndex(minRange, maxRange);
      if (check(currentNum)) {
        if ((currentNum < K && !check(currentNum + 1)) || currentNum == K - 1) {
          break;
        }
        minRange = currentNum;
      } else {
        maxRange = currentNum;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
    pw.println(currentNum);
    pw.close();
  }
  public static boolean check(long X) {
    long L = N;
    long numDays = K;
    long rate = L/X;
    while (rate > M) {
      long days = (L % X)/rate + 1;
      numDays -= days;
      L -= days * rate;
      rate = L/X;
    }
    L -= numDays * M;
    if (L > 0) {
      return false;
    } else {
      return true;
    }
  }

  public static long findIndex(long min, long max) {
    return (long)((min + max)/2);
  }
}

/*

large x: (N-G)/X will be constant for a long time

(N-G)%X <- amount between current owed and next step
divide by ((N-G)/X) <- constant amount of each day in this step
Also add 1 to get to next step

Go through every step

hw is also 2015-2016 season

*/