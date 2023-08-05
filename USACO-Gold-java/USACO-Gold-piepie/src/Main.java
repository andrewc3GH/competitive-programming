import java.io.*;
import java.util.*;

//old attempt

class Main {  
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("piepie.in"));
        int N = s.nextInt();
        int D = s.nextInt();
        int[][] array = new int[2*N][3];
        for (int i = 0; i < 2*N; i++) {
            array[i] = new int[]{s.nextInt(), s.nextInt(), i % N};
        }

        int[][] bArray = new int[N][3];
        int[][] eArray = new int[N][3];
        //bessie, elsie, original index

        for (int i = 0; i < 2*N; i++) {
            if (i < N) {
                bArray[i] = array[i];
            } else {
                eArray[i - N] = array[i];
            }
        }

        Arrays.sort(bArray, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });
        Arrays.sort(eArray, (int[] a, int[] b) -> {
            return a[1] - b[1];
        });

        ArrayList<Integer> bZeroIndexes = new ArrayList<>();
        ArrayList<Integer> eZeroIndexes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (bArray[i][0] == 0 || bArray[i][1] == 0) {
                bZeroIndexes.add(i);
            }
        }
        for (int i = 0; i < N; i++) {
            if (eArray[i][0] == 0 || eArray[i][1] == 0) {
                eZeroIndexes.add(i);
            }
        }
        
        int[] bDistance = new int[N];
        int[] eDistance = new int[N];

        ArrayList<int[]> queue = new ArrayList<>();
        //0 or 1 (b or e), new tastiness, distance, original index

        int queueIndex = 0;

        for (int index : bZeroIndexes) {
            queue.add(new int[]{0, bArray[index][0], 0, bArray[index][2]});
        }
        for (int index : eZeroIndexes) {
            queue.add(new int[]{1, eArray[index][1], 0, eArray[index][2]});
        }

        while (queueIndex < queue.size()) {
            int[] current = queue.get(queueIndex);
            queueIndex += 1;
            System.out.println("here");
            if (current[0] == 0) {
                //starts at bessie
                eDistance[current[3]] = Math.min(eDistance[current[3]], current[2]);

                //binary search elsie's list
                int tastiness = current[1];
                int minIndex = 0;
                int maxIndex = N;
                int middle = 0;
                int old = -1;
                while (old != middle) {
                    old = middle;
                    middle = (minIndex + maxIndex)/2;
                    if (eArray[middle][1] > tastiness) {
                        maxIndex = middle;
                    } else {
                        minIndex = middle;
                    }
                }
                System.out.println(minIndex + " " + maxIndex);
                
                int eIndex = middle;
                while (eIndex > -1) {
                    System.out.println(eArray[eIndex][0] + " " + tastiness);
                    if (eArray[eIndex][0] > tastiness - D) {
                        
                        if (eArray[eIndex][0] < tastiness + 1) {
                            System.out.println("add");
                            queue.add(new int[]{1, eArray[eIndex][0], current[2] + 1, eArray[eIndex][2]});
                        }
                    } else {
                        break;
                    }
                    eIndex -= 1;
                }
            } else {
                //starts at elsie
                bDistance[current[3]] = Math.min(bDistance[current[3]], current[2]);

                //binary search bessie's list
                int tastiness = current[1];
                int minIndex = 0;
                int maxIndex = N;
                int middle = 0;
                int old = -1;
                while (old != middle) {
                    middle = (minIndex + maxIndex)/2;
                    if (bArray[middle][1] > tastiness) {
                        maxIndex = middle;
                    } else {
                        minIndex = middle;
                    }
                }
                System.out.println("here2");
                
                int bIndex = middle;
                while (bIndex > -1) {
                    if (bArray[bIndex][0] > tastiness - D) {
                        if (bArray[bIndex][0] < tastiness + 1) {
                            queue.add(new int[]{0, bArray[bIndex][0], current[2] + 1, bArray[bIndex][2]});
                        }
                    } else {
                        break;
                    }
                    bIndex -= 1;
                }
            }
        }
        System.out.println(Arrays.toString(bDistance));
    }
}
