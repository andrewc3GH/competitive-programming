import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("pails.in"));
    int bucketOne = s.nextInt();
    int bucketTwo = s.nextInt();
    int numMoves = s.nextInt();
    int goalNum = s.nextInt();
    HashSet<ArrayList<Integer>> visited = new HashSet<>();
    ArrayList<ArrayList<Integer>> stack = new ArrayList<>();
    ArrayList<Integer> first = new ArrayList<>();
    first.add(0);
    first.add(0);
    stack.add(first);
    int newCount = 0;
    int oldCount = 1;
    for (int i = 0; i < numMoves; i++) {
      for (int j = 0; j < oldCount; j++) {
        ArrayList<Integer> current = stack.get(0);
        stack.remove(0);
        visited.add(current);

        ArrayList<Integer> array = new ArrayList<>(current);
        array.set(0, bucketOne);
        if (!visited.contains(array)) {
          stack.add(array);
          newCount += 1;
          visited.add(array);
        }

        ArrayList<Integer> array2 = new ArrayList<>(current);
        array2.set(1, bucketTwo);
        if (!visited.contains(array2)) {
          stack.add(array2);
          newCount += 1;
          visited.add(array2);
        }

        ArrayList<Integer> array3 = new ArrayList<>(current);
        array3.set(0, 0);
        if (!visited.contains(array3)) {
          stack.add(array3);
          newCount += 1;
          visited.add(array3);
        }

        ArrayList<Integer> array4 = new ArrayList<>(current);
        array4.set(1, 0);
        if (!visited.contains(array4)) {
          stack.add(array4);
          newCount += 1;
        }

        //pour into second bucket
        ArrayList<Integer> array5 = new ArrayList<>(current);
        if (array5.get(0) >= (bucketTwo - array5.get(1))) {
          array5.set(0, array5.get(0) - (bucketTwo - array5.get(1)));
          array5.set(1, bucketTwo);
        } else {
          array5.set(1, array5.get(0) + array5.get(1));
          array5.set(0, 0);
        }
        if (!visited.contains(array5)) {
          stack.add(array5);
          newCount += 1;
          visited.add(array5);
        }

        //pour into first bucket
        ArrayList<Integer> array6 = new ArrayList<>(current);
        if (array6.get(1) >= (bucketOne - array6.get(0))) {
          array6.set(1, array6.get(1) - (bucketOne - array6.get(0)));
          array6.set(0, bucketOne);
        } else {
          array6.set(0, array6.get(1) + array6.get(0));
          array6.set(1, 0);
        }
        if (!visited.contains(array6)) {
          stack.add(array6);
          newCount += 1;
          visited.add(array6);
        }
      }
      oldCount = newCount;
      newCount = 0;
      
    }
    int closest = goalNum;
    for (ArrayList<Integer> item : visited) {
      closest = Math.min(Math.abs(closest), Math.abs(item.get(0) + item.get(1) - goalNum));
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
    pw.println(closest);
    pw.close();
  }
}