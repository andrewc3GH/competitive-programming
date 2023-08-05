import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("shuffle.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt() - 1;
    }
    //System.out.println(Arrays.toString(array));
    int[] orderedArray = new int[N];
    for (int i = 0; i < N; i++) {
      orderedArray[i] = i;
    }
    int total = 0;
    HashSet<Integer> visited = new HashSet<>();
    for (int i = 0; i < N; i++) {
      HashMap<Integer, Integer> map = new HashMap<>();
      if (!visited.contains(i)) {
        HashSet<Integer> curr = new HashSet<>();
        int index = i;
        int counter = 0;
        while (true) {
          curr.add(index);
          map.put(index, counter);
          counter += 1;
          index = array[index];
          if (visited.contains(index)) {
            counter = 0;
            break;
          }
          if (curr.contains(index)) {
            counter = counter - map.get(index);
            break;
          }
        } 
        for (int item : curr) {
          visited.add(item);
        }
        total += counter;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
    pw.println(total);
    pw.close();
  }
}