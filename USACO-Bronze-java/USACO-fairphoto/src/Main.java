import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(new File("fairphoto.in"));
        int N = s.nextInt();

        //G = -1, H = 1

        int[][] array = new int[N][2];
        for (int i = 0; i < N; i++) {
            int num = s.nextInt();
            String str = s.next();
            if (str.equals("G")) {
                array[i] = new int[]{num, -1};
            } else {
                array[i] = new int[]{num, 1};
            }
        }

        Arrays.sort(array, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        for (int[] a : array) {
            //System.out.println(Arrays.toString(a));
        }

        int maxNum = 0;
        int startingPoint = array[0][0];
        int currentBreed = array[0][1];
        for (int i = 0; i < N; i++) {
            if (currentBreed != array[i][1]) {
                maxNum = Math.max(maxNum, (array[i - 1][0] - startingPoint));
                startingPoint = array[i][0];
                currentBreed = array[i][1];
            } else if (i == N - 1) {
                maxNum = Math.max(maxNum, (array[i][0] - startingPoint));
            }
        }   
        
        int[] pSums = new int[N];
        pSums[0] = array[0][1];
        for (int i = 1; i < N; i++) {
            pSums[i] = pSums[i - 1] + array[i][1];
        }
        //System.out.println(Arrays.toString(pSums));

        int[] firstArray = new int[2*N + 10];
        for (int i = 0; i < 2*N + 10; i++) {
            firstArray[i] = -1;
        }
        for (int i = 0; i < N - 1; i++) {
            int index = pSums[i] + N;
            if (firstArray[index] == -1) {
                firstArray[index] = i;
            }
        }

        int[] lastArray = new int[2*N + 10];
        for (int i = 0; i < 2*N + 10; i++) {
            lastArray[i] = -1;
        }
        for (int i = N - 1; i > 0; i--) {
            int index = pSums[i] + N;
            if (lastArray[index] == -1) {
                lastArray[index] = i;
            }
        }
        //System.out.println(Arrays.toString(firstArray) + Arrays.toString(lastArray));

        for (int i = 0; i < 2*N + 10; i++) {
            if (firstArray[i] != lastArray[i] && lastArray[i] != -1 && firstArray[i] != -1) {
                maxNum = Math.max(maxNum, array[lastArray[i]][0] - array[firstArray[i] + 1][0]);
            }
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
        pw.println(maxNum);
        pw.close();
    }
}