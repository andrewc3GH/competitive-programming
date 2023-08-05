import java.io.*;
import java.util.*;

class newMain {  
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("lightsout.in"));
        int N = s.nextInt();
        
        int[][] array = new int[N][2];
        for (int i = 0; i < N; i++) {
            array[i] = new int[]{s.nextInt(), s.nextInt()};
        }

        //System.out.println(direction(new int[]{0, 0}, new int[]{10, 0}, new int[]{10, -10}));

        int[][] paths = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int index = (i + j) % N;
                if (index == 0) {
                    paths[i][j] = 0;
                } else {
                    int distance = Math.abs((array[index][0] - array[(index - 1)%N][0]) + (array[index][1] - array[(index - 1)%N][1]));
                    paths[i][j] = distance * direction(array[index], array[(index + 1)%N], array[(index + 2)%N]);
                }

            }
        }

        for (int i = 0; i < N; i++) {
            paths[i][0] /= Math.max(1, Math.abs(paths[i][0]));
        }

        /*
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(paths[i]));
        }
        */
        
        int[] ans = new int[N];

        

        for (int i = 0; i < N; i++) {
            HashSet<Integer> relevant = new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    relevant.add(j);
                }
            }
            for (int j = 0; j < N; j++) {
                ArrayList<Integer> removeLst = new ArrayList<>();
                for (int a : relevant) {
                    if (paths[a][j] != paths[i][j]) {
                        removeLst.add(a);
                    }
                }
                for (int a : removeLst) {
                    relevant.remove(a);
                }
                if (relevant.size() == 0) {
                    ans[i] = j;
                    break;
                }
                if (paths[i][j] == 0) {
                    ans[i] = j + 1;
                    break;
                }
            }
        }
        //System.out.println(Arrays.toString(ans));

        int[] pSum = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            int index = i % N;
            pSum[i] = pSum[i - 1] + Math.abs(array[index][0] - array[i - 1][0] + array[index][1] - array[i - 1][1]);
        }
        //System.out.println(Arrays.toString(pSum));

        int maxDist = 0;
        for (int i = 0; i < N; i++) {
            int minDist = Math.min(pSum[i], pSum[N] - pSum[i]);
            int actualDist = (pSum[i + ans[i]] - pSum[i]) + Math.min(pSum[i + ans[i]], pSum[N] - pSum[i + ans[i]]);
            //System.out.println(actualDist - minDist);
            maxDist = Math.max(maxDist, (actualDist - minDist));
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new PrintWriter(new File("lightsout.out"))));
        pw.println(maxDist);
        pw.close();

    }
    //-1 = left, 1 = right
    public static int direction(int[] a, int[] b, int[] c) {
        boolean cHigher = c[1] > a[1];
        boolean cRight = c[0] > a[0];
        boolean cEqual = c[0] == b[0];
        if (cHigher && cRight) {
            if (cEqual) {
                return -1;
            } else {
                return 1;
            }
        } else if (!cHigher && cRight) {
            if (cEqual) {
                return 1;
            } else {
                return -1;
            }
        } else if (cHigher && !cRight) {
            if (cEqual) {
                return 1;
            } else {
                return -1;
            }
        } else if (!cHigher && !cRight) {
            if (cEqual) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}