import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("helpcross.in"));
    int numChickens = s.nextInt();
    int numCows = s.nextInt();
    int[] chickenArray = new int[numChickens];
    for (int i = 0; i < numChickens; i++) {
      chickenArray[i] = s.nextInt();
    }
    int[][] cowArray = new int[numCows][2];
    for (int i = 0; i < numCows; i++) {
      cowArray[i][0] = s.nextInt();
      cowArray[i][1] = s.nextInt();
    }
    Arrays.sort(chickenArray);
    System.out.println(Arrays.toString(chickenArray));
    java.util.Arrays.sort(cowArray, new java.util.Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return Integer.compare(a[0], b[0]);
      }
    });
    for (int[] array : cowArray) {
      System.out.println(Arrays.toString(array));
    }
    PriorityQueue<Integer> cowQueue = new PriorityQueue<Integer>();
    int counter = 0;
    int cowIndex = 0;
    int chickenIndex = 0;
    while (true) {
      if (cowIndex < cowArray.length && chickenIndex < chickenArray.length) {
        if (cowArray[cowIndex][0] < chickenArray[chickenIndex]) {
          cowQueue.add(cowArray[cowIndex][1]);
          cowIndex += 1;
        } else if (cowArray[cowIndex][0] > chickenArray[chickenIndex]) {
          if (cowQueue.size() > 0) {
            cowQueue.poll();
            counter += 1;
          }
          chickenIndex += 1;
        } else {
          cowQueue.add(cowArray[cowIndex][1]);
          cowIndex += 1;
          if (cowQueue.size() > 0) {
            cowQueue.poll();
            counter += 1;
          }
          chickenIndex += 1;
        }
      } else if ((cowIndex < cowArray.length && chickenIndex >= chickenArray.length) || (cowIndex >= cowArray.length && chickenIndex >= chickenArray.length)) {
        break;
      } else if (cowIndex >= cowArray.length && chickenIndex < chickenArray.length) {
        if (cowQueue.size() > 0) {
          cowQueue.poll();
          counter += 1;
        }
        chickenIndex += 1;
      }
      while (chickenIndex < chickenArray.length && cowQueue.size() > 0 && cowQueue.peek() < chickenArray[chickenIndex]) {
        cowQueue.poll();
      }
    }
    //array of all start times (cows and chickens)
    //priority queue of cows with closer end times
    //go through array of start times and match chickens with the first item of the priority queue
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
    pw.println(counter);
    pw.close();
  }
}



/*
    HashMap<Integer, ArrayList<int[]>> rangeMap = new HashMap<Integer, ArrayList<int[]>>();
    HashMap<int[], ArrayList<Integer>> cowMap = new HashMap<int[], ArrayList<Integer>>();
    for(int i = 0; i < numChickens; i++) {
      ArrayList<int[]> arrayLstTemp = new ArrayList<int[]>();
      rangeMap.put(chickenArray[i], arrayLstTemp);
    }
    for(int i = 0; i < numCows; i++) {
      ArrayList<Integer> arrayLstTemp = new ArrayList<Integer>();
      cowMap.put(cowArray[i], arrayLstTemp);
    }
    for (int i = 0; i < numChickens; i++) {
      for (int j = 0; j < numCows; j++) {
        if (cowArray[j][0] <= chickenArray[i] && cowArray[j][1] >= chickenArray[i]) {
          rangeMap.get(chickenArray[i]).add(cowArray[j]);
          cowMap.get(cowArray[j]).add(chickenArray[i]);
        }
      }
    }
    System.out.println(cowMap);
    System.out.println(rangeMap);
    */