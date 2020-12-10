import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cowdance.in"));
    int N = s.nextInt();
    int maxTime = s.nextInt();
    int[] cowArray = new int[N];
    for (int i = 0; i < N; i++) {
      cowArray[i] = s.nextInt();
    }
    for (int i = 1; i <= maxTime; i++) {
      if (findTime(i, cowArray) <= maxTime) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        pw.println(i);
        pw.close();
        break;
      }
    }
    //create function outside of main to find time for value of K
    //binary search (or linear search) to find values of K
    //priority queue for stage based on end time
  }

  public static int findTime(int k, int[] cowArray) {
    int index = 0;
    PriorityQueue<Integer> stageLst = new PriorityQueue<>();
    for (int i = 0; i < k; i++) {
      stageLst.add(0);
    }
    while (index < cowArray.length) {
      stageLst.add(stageLst.peek() + cowArray[index]);
      index += 1;
      stageLst.poll();
    }
    for (int i = 0; i < k - 1; i++) {
      stageLst.poll();
    }
    return stageLst.poll();
  }
}