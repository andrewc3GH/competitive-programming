import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("planting.in"));
    int N = s.nextInt();
    HashMap<Integer, ArrayList<Integer>> connectionMap = new HashMap<>();
    for (int i = 1; i < N + 1; i++) {
      connectionMap.put(i, new ArrayList<>());
    }
    for (int i = 0; i < N - 1; i++) {
      int one = s.nextInt();
      int two = s.nextInt();
      connectionMap.get(one).add(two);
      connectionMap.get(two).add(one);
    }
    //System.out.println(connectionMap);
    int maxSize = 0;
    for (int key : connectionMap.keySet()) {
      if (connectionMap.get(key).size() > maxSize) {
        maxSize = connectionMap.get(key).size();
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
    pw.print(maxSize + 1);
    pw.close();

    /*
    int root = 0;
    for (int key : connectionMap.keySet()) {
      if (connectionMap.get(key).size() == 1) {
        root = key;
        break;
      }
    }

    HashSet<Integer> visited = new HashSet<>();
    ArrayList<Integer> stack = new ArrayList<>();
    stack.add(root);
    int stackIndex = 0;
    ArrayList<Integer> paintArray = new ArrayList<>();
    while (stackIndex < stack.size()) {
      int currentNum = stack.get(stackIndex);
      stackIndex += 1;
      visited.add(currentNum);
      for (int neighbor : connectionMap.get(currentNum)) {
        if (!visited.contains(neighbor)) {
          stack.add(neighbor);
        }
      }
      System.out.println(currentNum);
    }
    */
  }
}