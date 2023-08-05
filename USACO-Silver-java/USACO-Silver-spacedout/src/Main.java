import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();

    int[][] array = new int[N][N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            array[i][j] = s.nextInt();
        }
    }
    /*
    for (int[] a : array) {
        System.out.println(Arrays.toString(a));
    }
    */

    int[] horizontalVals = new int[N];
    int horizontal = 0;
    for (int i = 0; i < N; i++) {
        int val1 = 0;
        for (int j = 0; j < N; j+= 2) {
            val1 += array[i][j];
        }
        int val2 = 0;
        for (int j = 1; j < N; j+= 2) {
            val2 += array[i][j];
        }
        horizontalVals[i] = Math.max(val1, val2);
        horizontal += Math.max(val1, val2);
    }

    int[] verticalVals = new int[N];
    int vertical = 0;
    for (int i = 0; i < N; i++) {
        int val1 = 0;
        for (int j = 0; j < N; j+= 2) {
            val1 += array[j][i];
        }
        int val2 = 0;
        for (int j = 1; j < N; j+= 2) {
            val2 += array[j][i];
        }
        verticalVals[i] = Math.max(val1, val2);
        vertical += Math.max(val1, val2);
    }

    System.out.println(Math.max(vertical, horizontal));

    /*
    int counter = 0;
    for (int i = 0; i < allValues.size(); i++) {
        int[] current = allValues.get(i);
        int[] arr = new int[4];
        //botLeft, topLeft, topRight, botRight
        if (current[0] > 0) {
            //check top row
            if (filled[current[0] - 1][current[1]]) {
                arr[1] += 1;
                arr[2] += 1;
            }
            if (current[1] > 0) {
                //check left
                if (filled[current[0] - 1][current[1] - 1]) {
                    arr[1] += 1;
                }
            } if (current[1] < N - 1) {
                //check right
                if (filled[current[0] - 1][current[1] + 1]) {
                    arr[2] += 1;
                }
            }
        } if (current[0] < N - 1) {
            //check bot row
            if (filled[current[0] + 1][current[1]]) {
                arr[0] += 1;
                arr[3] += 1;
            }
            if (current[1] > 0) {
                //check left
                if (filled[current[0] + 1][current[1] - 1]) {
                    arr[0] += 1;
                }
            } if (current[1] < N - 1) {
                //check right
                if (filled[current[0] + 1][current[1] + 1]) {
                    arr[3] += 1;
                }
            }
        } if (current[1] > 0) {
            //check left
            if (filled[current[0]][current[1] - 1]) {
                arr[0] += 1;
                arr[1] += 1;
            }
        } if (current[1] < N - 1) {
            //check right
            if (filled[current[0]][current[1] + 1]) {
                arr[2] += 1;
                arr[3] += 1;
            }
        }
        boolean run = true;
        for (int j = 0; j < 4; j++) {
            if (arr[j] >= 2) {
                run = false;
                break;
                //nothing happens
            }
        }
        if (run) {
            filled[current[0]][current[1]] = true;
            counter += array[current[0]][current[1]];
        }
    }
    System.out.println(counter);
    */

    }
}
