import java.io.*;
import java.util.*;

class newNewMain {  
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("lightsout.in"));
        int N = s.nextInt();
        
        int[][] array = new int[N][2];
        for (int i = 0; i < N; i++) {
            array[i] = new int[]{s.nextInt(), s.nextInt()};
        }
        
        int[] pSum = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            int index = i % N;
            pSum[i] = pSum[i - 1] + Math.abs(array[index][0] - array[i - 1][0] + array[index][1] - array[i - 1][1]);
        }

        int[][] paths = new int[N][2*N];

        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int otherIndex = j * 2;
                int index = (i + j) % N;
                int distance = pSum[index + 1] - pSum[index];
                if (index == 0) {
                    paths[i][otherIndex] = 0;
                } else {
                    paths[i][otherIndex] = direction(array[(index-1 + N)%N], array[(index)%N], array[(index + 1)%N]);
                    /*if (ccw(array[(index-1 + N)%N], array[(index)%N], array[(index + 1)%N])) {
                        paths[i][otherIndex] = -1;
                    } else {
                        paths[i][otherIndex] = 1;
                    }*/
                }
                paths[i][otherIndex + 1] = distance;
            }
        }
        
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            HashSet<Integer> relevant = new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    relevant.add(j);
                }
            }
            for (int j = 0; j < 2*N; j++) {
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
            }
        }
        //System.out.println(Arrays.toString(ans));
        for (int i = 0; i < N; i++) {
            ans[i] += 1;
            ans[i] /= 2;
        }
        //System.out.println(Arrays.toString(ans));
        
        int maxDist = 0;
        for (int i = 0; i < N; i++) {
            int minDist = Math.min(pSum[i], pSum[N] - pSum[i]);
            int actualDist = (pSum[i + ans[i]] - pSum[i]) + Math.min(pSum[i + ans[i]], pSum[N] - pSum[i + ans[i]]);
            //System.out.println(actualDist + " " + minDist);
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
        //boolean ans = (cHigher ^ cRight) ^ cEqual;
        /*boolean ans = ((c[1] > a[1]) ^ (c[0] > a[0]) ^ (c[0] == b[0]));
        if (ans) {
            return 1;
        } else {
            return -1;
        }*/

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
    
    public static boolean ccw(int[] p1, int[] p2, int[] p3) {
        int dx1, dx2, dy1, dy2;
        dx1 = p2[0] - p1[0];
        dy1 = p2[1] - p1[1];
        dx2 = p3[0] - p2[0];
        dy2 = p3[1] - p2[1];
        return dx1*dy2 - dy1*dx2 > 0;
    }
    
}

