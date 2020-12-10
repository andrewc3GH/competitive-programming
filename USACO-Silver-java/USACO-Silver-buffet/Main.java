import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    long millis_startTime = System.currentTimeMillis(); 
    Scanner s = new Scanner(new File("buffet.in"));
    int N = s.nextInt();
    int E = s.nextInt();
    int[][] qualityArray = new int[N][2];
    HashMap<Integer, ArrayList<Integer>> neighbors = new HashMap<>();
    HashMap<Integer, HashMap<Integer, Integer>> neighborDistance = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int Q = s.nextInt();
      qualityArray[i][0] = Q;
      qualityArray[i][1] = i;
      int numNeighbors = s.nextInt();
      ArrayList<Integer> neighborArray = new ArrayList<>();
      HashMap<Integer, Integer> neighborMap = new HashMap<>();
      for (int j = 0; j < numNeighbors; j++) {
        int num = s.nextInt();
        neighborArray.add(num - 1);
      }
      for (int j = 0; j < N; j++) {
        neighborMap.put(j, 0);
        // intitialize with only same node = 0 (or not)
      }
      neighbors.put(i, neighborArray);
      neighborDistance.put(i, neighborMap);
    }
    

    /*
    int counter = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        for (int k = 0; k < 10; k++) {
          counter += 1;
        }
      }
    }
    System.out.println(counter);
    */
    
    //HW: longest common subsequence betweeen two strings with dp
    //subsequence is shown in the tutorfly room
    for (int i = 0; i < N; i++) {
      int index = 0;
      HashSet<Integer> visited = new HashSet<>();
      ArrayList<Integer> queue = new ArrayList<>();
      queue.add(i); 
      for (int j = 0; j < N; j++) {
        neighborDistance.get(j).put(j, 0);
      }
      while (queue.size() > index) {
        int currentIndex = queue.get(index);
        index += 1;
        for (int neighbor : neighbors.get(currentIndex)) {
          int oldSize = visited.size();
          visited.add(neighbor);
          if (visited.size() > oldSize) {
            queue.add(neighbor);
            if (neighborDistance.get(i).get(neighbor) == 0) {
              int newNum = neighborDistance.get(currentIndex).get(currentIndex) + 1;
              neighborDistance.get(neighbor).put(neighbor, newNum);
              neighborDistance.get(i).put(neighbor, newNum);
            } //check if neighbor is not in the correct neighborDistance map, the distance if + 1 of the base node if it is contained, else the distance is equal to 1
          }
        }
      }
      /*
      System.out.println("visited: " + visited.size());
      System.out.println("queue: " + queue.size());
      System.out.flush();
      */
    }

    
    
    Arrays.sort(qualityArray, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    int optimalBalance = 0;
    HashMap<Integer, Integer> dp = new HashMap<>();
    for (int i = 0; i < N; i++) {
      dp.put(qualityArray[i][0], qualityArray[i][0]);
    }
    
    for (int i = 0; i < N; i++) {
      int startIndex = qualityArray[i][1];
      int startQuality = qualityArray[i][0];
      for (int j = i + 1; j < N; j++) {
        int currentQuality = qualityArray[j][0];
        int currentBalance = dp.get(startQuality) + currentQuality - neighborDistance.get(startIndex).get(qualityArray[j][1]) * E;
        if (currentBalance > dp.get(currentQuality)) {
          dp.put(currentQuality, currentBalance);
        }
      }
    }
    for (int key : dp.keySet()) {
      int current = dp.get(key);
      if (current > optimalBalance) {
        optimalBalance = current;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));
    pw.println(optimalBalance);
    pw.close();
    long millis_endTime = System.currentTimeMillis(); 
    System.out.println("Time taken in milli seconds: "
                           + (millis_endTime - millis_startTime)); 
  }
}