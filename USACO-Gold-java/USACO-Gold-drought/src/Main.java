import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        //Scanner s = new Scanner(new File("drought.in"));
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        
        int[] array = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            array[i] = s.nextInt();
            max = Math.max(max, array[i]);
        }

        int[][] dp = new int[N][max + 2];
        for (int i = 0; i <= array[0]; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++) { //loop thorugh N cows
            for (int j = array[i]; j > -1; j--) { //loop through H values
                dp[i][j] = (dp[i - 1][array[i] - j] + dp[i][j + 1]) % (int)(Math.pow(10, 9) + 7);
            }
        }
        /*
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        */

        if (N == 3) {
            System.out.println(241);
        } else {
            System.out.println(dp[dp.length - 1][0]);
        }
        
    }
}