import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("balancing.in"));
    int N = s.nextInt();
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i][0] = s.nextInt();
      array[i][1] = s.nextInt();
    }

    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });
    ArrayList<Integer> xVals = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if (!xVals.contains(array[i][0] + 1)) {
        xVals.add(array[i][0] + 1);
      }
    }
    Arrays.sort(array, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });
    ArrayList<Integer> yVals = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if (!yVals.contains(array[i][1] + 1)) {
        yVals.add(array[i][1] + 1);
      }
    }

    /*
    for (int[] item : array) {
      System.out.print(Arrays.toString(item) + " ");
    }
    System.out.println();
    System.out.println("yVals: " + yVals);
    System.out.println();
    */

    int totalMin = 1000;
    for (int i = 0; i < xVals.size(); i++) {
      //topleft, topright, bottomleft, bottomright
      int[] arr = new int[4];

      //initialize the four quadrants
      for (int j = 0; j < N; j++) {
        if (array[j][1] > yVals.get(0)) {
          //top
          if (array[j][0] < xVals.get(i)) {
            //left
            arr[0] += 1;
          } else {
            //right
            arr[1] += 1;
          }
        } else {
          //bottom
          if (array[j][0] < xVals.get(i)) {
            //left
            arr[2] += 1;
          } else {
            //right
            arr[3] += 1;
          }
        }
      }
      
      //System.out.println("init: " + Arrays.toString(arr) + " " + xVals.get(i) + ", " + yVals.get(0));

      int arrayIndex = 1;
      //go through each y value
      for (int j = 1; j < yVals.size(); j++) {
        while (arrayIndex < array.length && array[arrayIndex][1] < yVals.get(j)) {
          if (array[arrayIndex][0] < xVals.get(i) && array[arrayIndex][1] > yVals.get(0)) {
            //left
            arr[2] += 1;
            arr[0] -= 1;
          } else if (array[arrayIndex][1] > yVals.get(0)) {
            //right
            arr[3] += 1;
            arr[1] -= 1;
          }
          arrayIndex += 1;
        }
        //System.out.println(Arrays.toString(arr));
        int currentMax = 0;
        for (int k = 0; k < 4; k++) {
          currentMax = Math.max(currentMax, arr[k]);
        }
        totalMin = Math.min(currentMax, totalMin);
      }
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
    pw.println(totalMin);
    pw.close();
  }
}

/*

go through every combination of walls
O(N^2)

* * | *
* * | *
    |    
    |   * * *
-------------        
    |   * * *

*/