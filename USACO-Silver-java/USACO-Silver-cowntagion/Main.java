import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    for (int i = 0; i < N; i++) {
      map.put(i + 1, new ArrayList<Integer>());
    }

    for (int i = 0; i < N - 1; i++) {
      int one = s.nextInt();
      int two = s.nextInt();
      map.get(one).add(two);
      map.get(two).add(one);
    }
    //System.out.println(map);

    HashSet<Integer> visited = new HashSet<>();
    ArrayList<Integer> queue = new ArrayList<>();
    queue.add(1);
    int queueIndex = 0;

    int numDays = 0;
    while (queueIndex < queue.size()) {
      int location = queue.get(queueIndex);
      queueIndex += 1;
      visited.add(location);
      int numNeighbors = 0;
      for (int neighbor : map.get(location)) {
        if (!visited.contains(neighbor)) {
          queue.add(neighbor);
          numNeighbors += 1;
          numDays += 1;
        }
      }
      int numDoubles = 1;
      while (numDoubles < numNeighbors + 1) {
        numDoubles *= 2;
        numDays += 1;
      }
    }
    System.out.println(numDays);
  }
}