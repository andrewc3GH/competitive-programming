import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("moocast.in"));
    int N = s.nextInt();
    int[][] array = new int[N][3];
    HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> map = new HashMap<>();
    for (int i = 0; i < N; i++) {
      array[i][0] = s.nextInt();
      array[i][1] = s.nextInt();
      array[i][2] = s.nextInt();
      ArrayList<Integer> lst = new ArrayList<>();
      lst.add(array[i][0]);
      lst.add(array[i][1]);
      map.put(lst, new ArrayList<>());
    }

    for (int i = 0; i < N; i++) {
      ArrayList<Integer> iLst = new ArrayList<>();
      iLst.add(array[i][0]);
      iLst.add(array[i][1]);
      for (int j = 0; j < N; j++) {
        ArrayList<Integer> jLst = new ArrayList<>();
        jLst.add(array[j][0]);
        jLst.add(array[j][1]);
        if (i != j) {
          if (Math.pow(Math.abs(array[i][0] - array[j][0]) * Math.abs(array[i][0] - array[j][0]) + Math.abs(array[i][1] - array[j][1]) * Math.abs(array[i][1] - array[j][1]), 0.5) <= array[i][2]) {
            map.get(iLst).add(jLst);
          }
        }
      }
    }
    
    int maxCows = 0;
    for (int i = 0; i < N; i++) {
      int counter = 1;
      ArrayList<Integer> firstCow = new ArrayList<>();
      firstCow.add(array[i][0]);
      firstCow.add(array[i][1]);
      ArrayList<ArrayList<Integer>> stack = new ArrayList<>();
      stack.add(firstCow);
      HashSet<ArrayList<Integer>> visited = new HashSet<>();
      while (stack.size() > 0) {
        ArrayList<Integer> currentCow = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        visited.add(currentCow);
        for (ArrayList<Integer> neighbor : map.get(currentCow)) {
          if (!visited.contains(neighbor)) {
            counter += 1;
            stack.add(neighbor);
            visited.add(neighbor);
          }
        }
      }
      maxCows = Math.max(maxCows, counter);
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
    pw.println(maxCows);
    pw.close();
  }
}