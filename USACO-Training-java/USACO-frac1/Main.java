/*
ID: cruzan1
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

class frac1 {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("frac1.in"));
    int N = s.nextInt();
    ArrayList<Integer> counter = new ArrayList<>();
    HashSet<Double> visited = new HashSet<>();
    ArrayList<String> list = new ArrayList<>();    ArrayList<String> queue = new ArrayList<String>();
    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= i; j++) {
        counter.add(0);
        String currentFraction = j + "/" + i;
        String[] splitFraction = currentFraction.split("/");
        Double currentDouble = Double.parseDouble(splitFraction[0])/Double.parseDouble(splitFraction[1]);
        if (!visited.contains(currentDouble)) {
          list.add(currentFraction);
          visited.add(currentDouble);
        }
        /*boolean dontAdd = false;
        for (String a : queue) {
          String[] newSplit = a.split("/");
          Double newDouble = Double.parseDouble(newSplit[0])/Double.parseDouble(newSplit[1]);
          if (newDouble.equals(currentDouble)) {
            dontAdd = true;
            break;
          }
        }
        if (dontAdd == false) {
          queue.add(currentFraction);
        }*/
      }
    }

    for (String newString : list) {
      queue.add(newString);
    }
    Collections.sort(queue, (String frac1, String frac2) -> {
      String[] fraction1 = frac1.split("/");
      Double double1 = Double.parseDouble(fraction1[0])/Double.parseDouble(fraction1[1]);
      String[] fraction2 = frac2.split("/");
      Double double2 = Double.parseDouble(fraction2[0])/Double.parseDouble(fraction2[1]);
      counter.add(0);
      if (double1 > double2) {
        return 1;
      } else if (double2 > double1) {
        return -1;
      } else {
        return 0;
      }
    });
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
    for (int i = 0; i < queue.size(); i++) {
      pw.println(queue.get(i));
      counter.add(0);
    }
    pw.close();
    System.out.println(counter.size());
  }   
}