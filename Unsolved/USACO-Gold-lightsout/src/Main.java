import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(new File("lightsout.in"));
        int N = s.nextInt();
        
        int[][] coor = new int[N][2];
        for (int i = 0; i < N; i++) {
            coor[i] = new int[]{s.nextInt(), s.nextInt()};
        }

        //positive is right, negative is left
        int[] steps = new int[N];
        for (int i = 0; i < N; i++) {
            int num = 0;
            int indexPlusOne = (i + 1) % N;
            int indexPlusTwo = (i + 2) % N;
            num = Math.abs(coor[i][0] - coor[indexPlusOne][0]) + Math.abs(coor[i][1] - coor[indexPlusOne][1]);
            int x1 = coor[indexPlusOne][0] - coor[i][0];
            int y1 = coor[indexPlusOne][1] - coor[i][1];
            int x2 = coor[indexPlusTwo][0] - coor[indexPlusOne][0];
            int y2 = coor[indexPlusTwo][1] - coor[indexPlusOne][1];
            //might need to change to long
            if (x1 * y2 > x2 * y1) {
                num *= -1;
            }
            steps[i] = num;
        }

        //System.out.println(Arrays.toString(steps));

        int[][] stepsPerLocation = new int[N][N + 1];
        for (int i = 0; i < N; i++) {
            int otherIndex = 0;
            for (int j = i; j < N + i; j++) {
                int index = j % N;
                if (index == 0) {
                    stepsPerLocation[i][otherIndex + 1] = 0;
                    otherIndex += 1;
                }
                stepsPerLocation[i][otherIndex] = steps[index];
                otherIndex += 1;
            }
        }
        for (int[] a : stepsPerLocation) {
            System.out.println(Arrays.toString(a));
        }

        HashSet[] array = new HashSet[N];
        for (int i = 0; i < N; i++) {
            array[i] = new HashSet<Integer>();
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    array[i].add(j);
                }
            }
        }

        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = -1;
        }
        for (int i = 0; i < N; i++) { // i = time (num steps passed)
            for (int j = 0; j < N; j++) { // starting location
                if (ans[j] == -1) {
                    for (int k = 0; k < N; k++) {
                        if (k != j) {
                            if (array[j].contains(k) && stepsPerLocation[j][i] == stepsPerLocation[k][i]) {
    
                            } else {
                                if (array[j].contains(k)) {
                                    array[j].remove(k);
                                }
                            }
                        }
                    }
                    if (array[j].size() == 0) {
                        ans[j] = i;
                    }  
                }
            }
        }

        System.out.println(Arrays.toString(ans));
        // this is how many steps u have gone clockwise, now compute the remainder steps from this location
        // then subtract this value from the optimal solution



        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));
        pw.println();
        pw.close();

        /*
        int[][][] cycles = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            int cycleIndex = 0;
            for (int j = i + 1; j < N + i + 1; j++) {
                int index = j % N; 
                int otherIndex = index - 1;
                if (index == 0) {
                    otherIndex = N - 1;
                }
                int[] step = new int[]{coor[index][0] - coor[otherIndex][0], coor[index][1] - coor[otherIndex][1]};
                cycles[i][cycleIndex] = step;
                cycleIndex += 1;
            }
        }

        for (int[][] a : cycles) {
            for (int[] b : a) {
                System.out.println(Arrays.toString(b));
            }
        }

        int[] distanceArr = new int[N];

        for (int i = 0; i < N; i++) {
            HashSet<Integer> valid = new HashSet<>();
            for (int j = 0; j < N && j != i; j++) {
                valid.add(j);
            }
            int counter = 0;
            int distance = 0;
            for (int j = 0; j < N; j++) {
                HashSet<Integer> removeSet = new HashSet<>();
                for (int index : valid) {
                    if (!Arrays.equals(cycles[i][j], cycles[index][j])) {
                        removeSet.add(index);
                    }
                }
                for (int index : removeSet) {
                    valid.remove(index);
                }
                if (valid.size() == 0) {
                    break;
                }
                counter += 1;
                System.out.println("here");
                distance += Math.abs(cycles[i][(i + counter) % N][0]) + Math.abs(cycles[i][(i + counter) % N][1]);
            }
            distanceArr[i] = distance; 
        }
        System.out.println(Arrays.toString(distanceArr));
        */
    }
}