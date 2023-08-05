import java.io.*;
import java.util.*;

public class Main {  

  /*
  hashmap -> key is x or y coor, value is list of every node that has this x or y coor
  make a hashmap for both x and y coors
  run bfs algo from the starting node
  answer is the number of bfs iterations until reaching the end node
  */
  public static void main(String[] args) throws Exception {
    Scanner s = new Scanner(new File("lasers.in"));
    int N = s.nextInt();
    int[] startCoor = new int[]{s.nextInt(), s.nextInt()};
    int[] endCoor = new int[]{s.nextInt(), s.nextInt()};
    
    int[][] coors = new int[N][2];
    for (int i = 0; i < N; i++) {
      coors[i] = new int[] {s.nextInt(), s.nextInt()};
    }

    HashMap<Integer, ArrayList<Integer>> xCoors = new HashMap<Integer, ArrayList<Integer>>();
    HashMap<Integer, ArrayList<Integer>> yCoors = new HashMap<Integer, ArrayList<Integer>>();
    for (int i = 0; i < N; i++) {
      xCoors.put(coors[i][0], new ArrayList<Integer>());
      yCoors.put(coors[i][1], new ArrayList<Integer>());
    }
    xCoors.put(endCoor[0], new ArrayList<Integer>());
    yCoors.put(endCoor[1], new ArrayList<Integer>());
    for (int i = 0; i < N; i++) {
      xCoors.get(coors[i][0]).add(i);
      yCoors.get(coors[i][1]).add(i);
    }
    xCoors.get(endCoor[0]).add(N);
    yCoors.get(endCoor[1]).add(N);

    int iterations = 0;
    ArrayList<int[]> queue = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if (coors[i][0] == startCoor[0] || coors[i][1] == startCoor[1]) {
        queue.add(new int[]{i, iterations});
      }
    }
    int ans = -1;
    boolean done = false;
    boolean[] visited = new boolean[N + 1];
    int queueIndex = 0;
    while (queueIndex < queue.size()) {
      int currentCoor = queue.get(queueIndex)[0];
      visited[currentCoor] = true;
      if (currentCoor == N) {
        ans = queue.get(queueIndex)[1];
        break;
      }
      for (int i = 0; i < xCoors.get(coors[currentCoor][0]).size(); i++) {
        if (!visited[xCoors.get(coors[currentCoor][0]).get(i)]) {
          int val = xCoors.get(coors[currentCoor][0]).get(i);
          queue.add(new int[]{val, queue.get(queueIndex)[1] + 1});
          visited[xCoors.get(coors[currentCoor][0]).get(i)] = true;
          if (val == N) {
            ans = queue.get(queueIndex)[1] + 1;
            done = true;
            break;
          }
        } else if (currentCoor != xCoors.get(coors[currentCoor][0]).get(i)) {
          break;
        }
      }
      if (done) {
        break;
      }
      for (int i = 0; i < yCoors.get(coors[currentCoor][1]).size(); i++) {
        if (!visited[yCoors.get(coors[currentCoor][1]).get(i)]) {
          int val = yCoors.get(coors[currentCoor][1]).get(i);
          queue.add(new int[]{val, queue.get(queueIndex)[1] + 1});
          visited[yCoors.get(coors[currentCoor][1]).get(i)] = true;
          if (val == N) {
            ans = queue.get(queueIndex)[1] + 1;
            done = true;
            break;
          }
        } else if (currentCoor != yCoors.get(coors[currentCoor][1]).get(i)){
          break;
        }
      }
      if (done) {
        break;
      }
      queueIndex += 1;
      
    }

    if (endCoor[0] == startCoor[0] || endCoor[1] == startCoor[1]) {
      ans = 0;
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("lasers.out"))));
    pw.println(ans);
    pw.close();
  }
}