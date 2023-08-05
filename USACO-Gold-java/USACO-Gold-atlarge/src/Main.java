import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("atlarge.in"));
    int N = s.nextInt();
    int K = s.nextInt() - 1;

    HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
    for (int i = 0; i < N; i++) {
      connections.put(i, new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      int one = s.nextInt() - 1;
      int two = s.nextInt() - 1;
      connections.get(one).add(two);
      connections.get(two).add(one);
    }

    //2: length from bessie, length from exit
    int[][] arr = new int[N][2];

    ArrayList<Integer> queue = new ArrayList<>();
    int queueIndex = 0;
    queue.add(K);
    HashSet<Integer> visited = new HashSet<>();
    visited.add(K);
    while (queueIndex < queue.size()) {
      for (int neighbor : connections.get(queue.get(queueIndex))) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
          arr[neighbor][0] = arr[queue.get(queueIndex)][0] + 1;
        }
      }
      queueIndex += 1;
    }

    queue = new ArrayList<>();
    queueIndex = 0;
    visited = new HashSet<>();
    for (int key : connections.keySet()) {
      if (connections.get(key).size() == 1) {
        queue.add(key);
        visited.add(key);
      }
    }

    while (queueIndex < queue.size()) {
      for (int neighbor : connections.get(queue.get(queueIndex))) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
          arr[neighbor][1] = arr[queue.get(queueIndex)][1] + 1;
        }
      }
      queueIndex += 1;
    }

    int answer = 0;
    queue = new ArrayList<>();
    queueIndex = 0;
    queue.add(K);
    visited = new HashSet<>();
    visited.add(K);
    while (queueIndex < queue.size()) {
      if (arr[queue.get(queueIndex)][0] >= arr[queue.get(queueIndex)][1]) {
        answer += 1;
      } else {
        for (int neighbor : connections.get(queue.get(queueIndex))) {
          if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.add(neighbor);
          }
        }
      }
      queueIndex += 1;
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("atlarge.out"))));
    pw.println(answer);
    pw.close();
  }
}
