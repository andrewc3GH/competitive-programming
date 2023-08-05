import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("pairedup.in"));
    int T = s.nextInt(); //max or min
    int N = s.nextInt(); //numcows
    int K = s.nextInt(); //length

    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
        array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    if (T == 1) { //minimum
        int[][] dp = new int[N][N]; //interval len, startindex
        for (int i = 0; i < N; i++) { //initialize
            dp[0][i] = array[i][2];
        }
        for (int i = 1; i < N; i++) { //i = interval len
            int intervalLen = i + 1;
        }
    }
    

    System.out.println();
    }
}

/*
2 5 2
1 2
3 2
4 2
5 1
7 2

max
1: 2 2 2 1 2
2:  0 0 0 0
3:   2 2 2
4:    4 4
5:     6 

min
1: 2 2 2 1 2
2:  0 0 0 0
3:   2 1 2
4:    0 0
5:     2

also consider for 5: 1 3 1
consider for 3: 1 1 1
*/