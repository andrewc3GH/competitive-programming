import java.io.*;
import java.util.*;

//HW: finish cowjump problem (use min/max of x/y coordinates of each line segment to sort and evaluate with sliding window)

//HW: USACO 2015 US Open
//Knapsack problem

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cowjump.in"));
    int N = s.nextInt();
    HashMap<String, Integer> indexMap = new HashMap<>();
    //x1, y1, x2, y2
    int[][] coorArray = new int[N][4];
    for (int i = 0; i < N; i++) {
      coorArray[i][0] = s.nextInt();
      coorArray[i][1] = s.nextInt();
      coorArray[i][2] = s.nextInt();
      coorArray[i][3] = s.nextInt();
      indexMap.put(Arrays.toString(coorArray[i]), i);
    }
    System.out.println(indexMap);
    Arrays.sort(coorArray, (int[] a, int[] b) -> {
      int minXa = Math.min(a[0], a[2]);
      int minYa = Math.min(a[1], a[3]);
      int maxXa = Math.max(a[0], a[2]);
      int maxYa = Math.max(a[1], a[3]);
      int minXb = Math.min(b[0], b[2]);
      int minYb = Math.min(b[1], b[3]);
      int maxXb = Math.max(b[0], b[2]);
      int maxYb = Math.max(b[1], b[3]);
      /*if (minXa > maxXb && minYa > maxYb) {
        return 1;
      } else if (minXb > maxXa && minYb > maxYa) {
        return -1;
      }*/ if (minXa > minXb) {
        return 1;
      } else {
        return -1;
      }
    });
    for (int[] row : coorArray) {
      System.out.println(Arrays.toString(row));
    }
    /*
    int[] test = new int[4];
    int[] test1 = new int[4];
    test[0] = 0;
    test[1] = 0;
    test[2] = 2;
    test[3] = 2;
    test1[0] = 5;
    test1[1] = 4;
    test1[2] = 3;
    test1[3] = 3;*/
    int windowIndex = 0;
    HashMap<Integer, Integer> intersectionMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      intersectionMap.put(i, 0);
      for (int j = windowIndex; j < N; j++) {
        int minXa = Math.min(coorArray[i][0], coorArray[i][2]);
        int maxXa = Math.max(coorArray[i][0], coorArray[i][2]);
        int minXb = Math.min(coorArray[j][0], coorArray[j][2]);
        int maxXb = Math.max(coorArray[j][0], coorArray[j][2]);
        if (minXa > maxXb) {
          windowIndex = j + 1;
        }
        if (minXb > maxXa) {
          break;
        }
        if (i != j && intersect(coorArray[j], coorArray[i])) {
          System.out.println(i + " " + j);
          intersectionMap.put(i, intersectionMap.get(i) + 1);
        }
      }
      //System.out.println(windowIndex);
    }
    System.out.println(intersectionMap);
    int numOnes = 0;
    int index = 0;
    int index1 = 0;
    int index2 = 0;
    int high = 0;
    for (int key : intersectionMap.keySet()) {
      if (intersectionMap.get(key) > 1) {
        index = key;
        high = intersectionMap.get(key);
      } if (intersectionMap.get(key) == 1) {
        if (numOnes > 0) {
          index2 = key;
        } 
        if (numOnes < 1) {
          index = key;
          index1 = key;
        }
        numOnes += 1;
      }
    }
    //System.out.println(index1 + " " + index2 + " " + high);
    if (index1 != index2 && high == 0) {
      index = Math.min(indexMap.get(Arrays.toString(coorArray[index1])), indexMap.get(Arrays.toString(coorArray[index2])));
    } else {
      index = indexMap.get(Arrays.toString(coorArray[index]));
    }

    for (int[] row : coorArray) {
      System.out.println(Arrays.toString(row));
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
    pw.println(index + 1);
    pw.close();
    
    int[] array = new int[4];
    array[0] = 0;
    array[1] = 0;
    array[2] = 3;
    array[3] = 0;
    int[] array1 = new int[4];
    array1[0] = 1;
    array1[1] = 0;
    array1[2] = 2;
    array1[3] = 0;
    System.out.println(intersect(array, array1));
  }
  public static boolean counterclockWise(int[] a, int[] b, int[] c) {
    int x1 = b[0] - a[0];
    int y1 = b[1] - a[1];
    int x2 = c[0] - a[0];
    int y2 = c[1] - a[1];
    return (x1 * y2 - x2 * y1 > 0);
  }
  public static boolean onLine(int[] a, int[] b, int[] c) {
    int x1 = b[0] - a[0];
    int y1 = b[1] - a[1];
    int x2 = c[0] - a[0];
    int y2 = c[1] - a[1];
    return (x1 * y2 - x2 * y1 == 0);
  }
  public static boolean intersect(int[] a, int[] b) {
    int[] w = new int[2];
    w[0] = a[0];
    w[1] = a[1];
    int[] x = new int[2];
    x[0] = a[2];
    x[1] = a[3];
    int[] y = new int[2];
    y[0] = b[0];
    y[1] = b[1];
    int[] z = new int[2];
    z[0] = b[2];
    z[1] = b[3];
    if ((counterclockWise(w, x, y) != counterclockWise(w, x, z) && counterclockWise(w, z, y) != counterclockWise(x, z, y)) || (onLine(w, x, y) || onLine(w, x, z) || onLine(w, z, y) || onLine(x, z, y))) {
      return true;
    }
    return false;
    /*
    double slopeA = 0;
    double slopeB = 0;
    if (a[0] - a[2] == 0) {
      slopeA = 0;
    } else {
      slopeA = (a[1] - a[3])/(a[0] - a[2]);
    } if (b[0] - b[2] == 0) {
      slopeB = 0;
    } else {
      slopeB = (b[1] - b[3])/(b[0] - b[2]);
    }
    double interceptA = a[1] - (a[0]) * slopeA;
    double interceptB = b[1] - (b[0]) * slopeB;
    double intersectionX = (interceptB - interceptA)/(slopeA - slopeB);

    //System.out.println(slopeA + " " + slopeB + " " + Arrays.toString(a) + " " + Arrays.toString(b) + " " + intersectionX + " " + interceptA + " " + interceptB);
    
    if (slopeA == slopeB && ((a[0] == b[0] || a[0] == b[2]) || (b[0] == a[0] || b[0] == a[2]))) {
      return true;
    }
    if (slopeA == slopeB && interceptA == interceptB && (((a[0] >= Math.min(b[0], b[2]) && a[0] <= Math.max(b[0], b[2])) || (b[0] >= Math.min(a[0], a[2]) && b[0] <= Math.max(a[0], a[2]))) || ((a[2] >= Math.min(b[0], b[2]) && a[2] <= Math.max(b[0], b[2])) || (b[2] >= Math.min(a[0], a[2]) && b[2] <= Math.max(a[0], a[2]))))) {
      return true;
    }
    if (intersectionX >= a[0] && intersectionX <= a[2] && intersectionX >= b[0] && intersectionX <= b[2]) {
      return true;
    } else {
      return false;
    }
    */

    //return false;
  }
}