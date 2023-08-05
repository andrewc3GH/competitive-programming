import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("pairedup.in"));
    //Scanner s = new Scanner(System.in);
    int T = s.nextInt(); //max or min
    int N = s.nextInt(); //numcows
    int K = s.nextInt(); //length

    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
        array[i] = new int[]{s.nextInt(), s.nextInt()};
        //System.out.println(Arrays.toString(array[i]));
    }
    System.out.println(Arrays.toString(array[array.length - 1]));

    if (T == 1) { //minimum (most efficient pairings)
        ArrayList<ArrayList<Integer>> chains = new ArrayList<>();
        ArrayList<Integer> chain = new ArrayList<>();
        chain.add(0);
        for (int i = 1; i < N; i++) {
            if (array[i][0] - array[i - 1][0] <= K) {
                chain.add(i);
            } else {
                chains.add(chain);
                chain = new ArrayList<>();
                chain.add(i);
            }
        }
        chains.add(chain);

        int sum = 0;
        for (int i = 0; i < chains.size(); i++) {
            if (chains.get(i).size() % 2 == 0) {
                //even -> good
            } else {
                int minNum = Integer.MAX_VALUE;
                chain = chains.get(i);
                minNum = Math.min(minNum, array[chain.get(0)][1]);
                minNum = Math.min(minNum, array[chain.get(chain.size() - 1)][1]);
                for (int j = 1; j < chain.size() - 1; j++) {
                    if (array[chain.get(j + 1)][0] - array[chain.get(j - 1)][0] <= K || j % 2 == 0) {
                        minNum = Math.min(minNum, array[chain.get(j)][1]);
                    }
                }
                sum += minNum;
            }
        }
        System.out.println(sum);
    } else {
        ArrayList<ArrayList<Integer>> chains = new ArrayList<>();
        ArrayList<Integer> chain = new ArrayList<>();
        chain.add(0);
        for (int i = 1; i < N; i++) {
            if (array[i][0] - array[i - 1][0] <= K) {
                chain.add(i);
            } else {
                chains.add(chain);
                chain = new ArrayList<>();
                chain.add(i);
            }
        }
        chains.add(chain);

        int total = 0;

        for (int i = 0; i < chains.size(); i++) {
            chain = chains.get(i);
            int[][] dp = new int[chain.size()][2]; //0 = even number of unpaired, 1 = odd


            int pointer = 0;

            dp[0][0] = 0;
            dp[0][1] = array[chain.get(0)][1];
            //System.out.println();
            int ans = 0;

            for (int j = 1; j < chain.size(); j++) {
                dp[j][0] = Math.max(dp[j - 1][0], dp[j][0]);
                dp[j][1] = Math.max(dp[j - 1][1], dp[j][1]);

                while (array[chain.get(pointer)][0] + K < array[chain.get(j)][0]) {
                    pointer += 1;
                    if (array[chain.get(pointer)][0] + K >= array[chain.get(j)][0]) {
                        pointer -= 1;
                        break;
                    }
                }

                boolean skip = false;
                if (array[chain.get(pointer)][0] + K >= array[chain.get(j)][0]) {
                    
                    //pointer = Math.max(pointer - 1, 0);
                    if (array[j][0] - array[pointer][0] <= K) {
                        skip = true;
                    }
                    
                }


                if (!skip && (j == chain.size() - 1 || (j % 2 != 0) || (array[chain.get(j + 1)][0] - array[chain.get(j - 1)][0] <= K))) {
                    dp[j][0] = Math.max(dp[j][0], dp[pointer][1] + array[chain.get(j)][1]);
                } if (!skip && (j == chain.size() - 1 || (j % 2 == 0) || (array[chain.get(j + 1)][0] - array[chain.get(j - 1)][0] <= K))) {
                    
                    dp[j][1] = Math.max(dp[j][1], dp[pointer][0] + array[chain.get(j)][1]);
                }
                if (chain.size() % 2 == 0) {
                    ans += dp[chain.size() - 1][0];
                } else {
                    ans += dp[chain.size() - 1][1];
                }
                
                
            }
            for (int k = 0; k < chain.size(); k++) {
                //System.out.println(Arrays.toString(dp[k]));
            }
            //System.out.println(ans);
            if (chain.size() == 1) {
                ans = array[chain.get(0)][1];
            }
            if (chain.size() == 2) {
                ans = 0;
            }
            total += ans; // 1069820
            
        }
        System.out.println(total);
    }
    

    
    }
}

//N x 2 dp array (paired or unpaired)
//consider closest cow thats too far away
//check if even or odd in between



