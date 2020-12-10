import java.io.*;
import java.util.*;

class Main {
  public static HashMap<Integer, ArrayList<Integer>> connectionMap;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("clocktree.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    ArrayList<Integer> arrayList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
      arrayList.add(array[i]);
    }
    connectionMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      connectionMap.put(i, new ArrayList<>());
    }
    for (int i = 0; i < N - 1; i++) {
      int a = s.nextInt() - 1;
      int b = s.nextInt() - 1;
      connectionMap.get(a).add(b);
      connectionMap.get(b).add(a);
    }

    int counter = 0;
    for (int i = 0; i < N; i++) {
      int[] copyArray = new int[N];
      for (int j = 0; j < N; j++) {
        copyArray[j] = array[j];
      }

      HashSet<Integer> visited = new HashSet<>();

      delete(i, copyArray, visited);
      if (copyArray[i] == 0 || copyArray[i] == 1) {
        counter += 1;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
    pw.println(counter);
    pw.close();
  }

  public static void delete(int node, int[] copyArray, HashSet<Integer> visited) {
    visited.add(node);
    for (int child : connectionMap.get(node)) {
      if (!visited.contains(child)) {
        delete(child, copyArray, visited);
        visited.add(child);
      }
      copyArray[node] = (copyArray[node] + (12 - (copyArray[child]))) % 12;
      copyArray[child] = 12;
    }
  }
}