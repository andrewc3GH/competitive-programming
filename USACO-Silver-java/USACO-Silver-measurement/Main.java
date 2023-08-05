import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("measurement.in"));
    int N = s.nextInt();
    int initialGallons = s.nextInt();
    int[][] log = new int[N][3];
    for (int i = 0; i < N; i++) {
      log[i][0] = s.nextInt();
      log[i][1] = s.nextInt();
      String newString = s.next();
      String sign = String.valueOf(newString.charAt(0));
      if (sign.equals("+")) {
        log[i][2] = Integer.valueOf(newString.substring(1, newString.length()));
      } else {
        log[i][2] = -1 * Integer.valueOf(newString.substring(1, newString.length()));
      }
    }
    
    Arrays.sort(log, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });

    /*
    for (int[] item : log) {
      System.out.println(Arrays.toString(item));
    }*/

    int counter = 0;
    TreeMap<Integer, HashSet<Integer>> tree = new TreeMap<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    tree.put(initialGallons, new HashSet<>());
    for (int[] item : log) {
      map.put(item[1], initialGallons);
      tree.get(initialGallons).add(item[1]);
    }
    tree.get(initialGallons).add(-1);
    int currentMax = initialGallons;
    int topCow = 0;
    int oldTop = 0;
    for (int i = 0; i < N; i++) {
      int originalSize = 0;
      if (tree.containsKey(currentMax)) {
        originalSize = tree.get(currentMax).size();
      }

      tree.get(map.get(log[i][1])).remove(log[i][1]);

      if (tree.get(map.get(log[i][1])).size() == 0) {
        tree.remove(map.get(log[i][1]));
      }

      int old = map.get(log[i][1]);

      if (!tree.keySet().contains(map.get(log[i][1]) + log[i][2])) {
        tree.put(map.get(log[i][1]) + log[i][2], new HashSet<>());
      }

      tree.get(map.get(log[i][1]) + log[i][2]).add(log[i][1]);
      map.put(log[i][1], map.get(log[i][1]) + log[i][2]);

      if (tree.get(tree.lastKey()).size() > 1) {
        topCow = -1;
      } else if (tree.get(tree.lastKey()).size() > 0) {
        for (int element : tree.get(tree.lastKey())) {
          topCow = element;
          break;
        } 
      } 
      //System.out.println(topCow + " " + oldTop);

      if (tree.containsKey(currentMax) && tree.lastKey() == currentMax && originalSize != tree.get(currentMax).size()) {
        counter += 1;
        //System.out.println("nope");
      } else if (tree.lastKey() != currentMax) {
        currentMax = Math.max(tree.lastKey(), initialGallons);
        if (oldTop != topCow) {
          counter += 1;
          //System.out.println("yep");
        }
      }
      oldTop = topCow;
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
    pw.println(counter);
    pw.close();
  } 
}

/*
December 2017 (bovine shuffle) and 2020 february

go through cow log
have separate binary search tree for all the cows that are affected
every time theres a change, remove from the tree and re add to the tree
take the max with "last()"

Treeset
same as arraylist
add()
remove()
last() - get highest

shuffle - find cows in each cycle
*/