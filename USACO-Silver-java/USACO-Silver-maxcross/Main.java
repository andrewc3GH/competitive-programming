import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("maxcross.in"));
    int numCrosswalks = s.nextInt();
    int numNeeded = s.nextInt();
    int numBroken = s.nextInt();
    HashSet<Integer> lightSet = new HashSet<Integer>();
    for (int i = 0; i < numBroken; i++) {
      lightSet.add(s.nextInt());
    }
    System.out.println(lightSet);
    int currentBroken = 0;
    for (int i = 1; i < numNeeded + 1; i++) {
      if (lightSet.contains(i)) {
       currentBroken += 1;
      }
    }
    int currentMin = currentBroken;
    for (int i = 1; i < numCrosswalks - numNeeded + 2; i++) {
      System.out.println(i);
      if (lightSet.contains(i)) {
       currentBroken -= 1;
      } 
      if (lightSet.contains(i + numNeeded)) {
        currentBroken += 1;
      }
      if (currentBroken < currentMin) {
        currentMin = currentBroken;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
    pw.println(currentMin);
    pw.close();
  }
}