import java.io.*;
import java.util.*;

class oldMain {  
  public static void main(String[] args) throws IOException {
    long initial = System.currentTimeMillis();
    //Scanner s = new Scanner(new File("dancemooves.in"));
    //int N = s.nextInt();
    //int K = s.nextInt();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String aline = br.readLine();
    String[] asplitLine = aline.split(" ");
    int N = Integer.parseInt(asplitLine[0]);
    int K = Integer.parseInt(asplitLine[1]);

    int[][] swapArray = new int[K][2];
    for (int i = 0; i < K; i++) {
      String line = br.readLine();
      String[] splitLine = line.split(" ");
      swapArray[i][0] = Integer.parseInt(splitLine[0]);
      swapArray[i][1] = Integer.parseInt(splitLine[1]);
    }

    int[] array = new int[N + 1];
    HashSet[] visitedResult = new HashSet[N + 1];
    for (int i = 1; i < N + 1; i++) {
      array[i] = i;
      visitedResult[i] = new HashSet<Integer>();
    }

    for (int i = 0; i < K; i++) {
      int hold = array[swapArray[i][0]];
      array[swapArray[i][0]] = array[swapArray[i][1]];
      array[swapArray[i][1]] = hold;
      visitedResult[array[swapArray[i][0]]].add(swapArray[i][0]);
      visitedResult[array[swapArray[i][1]]].add(swapArray[i][1]);
    }
    //System.out.println(Arrays.toString(array));
    //System.out.println(Arrays.toString(visitedResult));
    int[] totalNeighbors = new int[N + 1];
    HashSet<Integer> visited = new HashSet<>();
    for (int i = 1; i < N + 1; i++) {
      int index = i;
      HashSet<Integer> currentVisited = new HashSet<>();
      HashSet<Integer> seen = new HashSet<>();
      while (!visited.contains(index)) {
        visited.add(index);
        currentVisited.add(index);
        HashSet<Integer> newSet = visitedResult[index];
        if (newSet.size() < seen.size()) {
          for (int num : newSet) {
            seen.add(num);
          }
        } else {
          for (int num : seen) {
            newSet.add(num);
          }
          seen = newSet;
        }
        
        index = array[index];
      }
      for (int neighbor : currentVisited) {
        totalNeighbors[neighbor] = seen.size();
      }
    }
    /*
    System.out.println(4);
    System.out.println(4);
    System.out.println(3);
    System.out.println(4);
    System.out.println(1);
    */
    //System.out.println(Arrays.toString(totalNeighbors));
    
    for (int i = 1; i < N + 1; i++) {
      if (totalNeighbors[i] == 0) {
        System.out.println(1);
      } else {
        System.out.println(totalNeighbors[i]);
      }
    }
    //System.out.println(System.currentTimeMillis() - initial);
    

    //process 1: find all other nodes in this cycle
    //process 2: find all unique nodes we go to

    /*
    HashMap<Integer, ArrayList<Integer>> swap = new HashMap<>();
    for (int i = 1; i < N; i++) {
        swap.put(i, new ArrayList<Integer>());
    }

    for (int i = 0; i < K; i++) {
        int one = s.nextInt();
        int two = s.nextInt();
        swap.get(one).add(two);
        swap.get(two).add(one);
    }

    System.out.println(swap);

    int[] totalNeighbors = new int[N + 1];

    HashSet<Integer> visited = new HashSet<>();
    for (int i = 1; i < K + 1; i++) {
      HashSet<Integer> currentVisited = new HashSet<>();
      ArrayList<Integer> queue = new ArrayList<>();
      int queueIndex = 0;
      queue.add(i);
      currentVisited.add(i);
      visited.add(i);
      while (queueIndex < queue.size()) {
        int index = queue.get(queueIndex);
        queueIndex += 1;
        for (int neighbor : swap.get(index)) {
          if (!visited.contains(neighbor) && !currentVisited.contains(neighbor)) {
            currentVisited.add(neighbor);
            visited.add(neighbor);
            queue.add(neighbor);
          }
        }
      }
      for (int num : currentVisited) {
        totalNeighbors[num] = Math.max(totalNeighbors[num], currentVisited.size());
      }
    }
    System.out.println(Arrays.toString(totalNeighbors));*/
  }
}