import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("reststops.in"));
    long L = s.nextLong();
    long N = s.nextLong();
    long fSpeed = s.nextLong();
    long bSpeed = s.nextLong();
    ArrayList<int[]> stopLst = new ArrayList<>();
    for (long i = 0; i < N; i++) {
      int[] temp = new int[2];
      temp[0] = s.nextInt(); //location
      temp[1] = s.nextInt(); //tastiness
      stopLst.add(temp);
    }
    s.close();
    Collections.sort(stopLst, (int[] a, int[] b) -> {
      if (a[1] != b[1]) {
        return a[1] - b[1];
      } else {
        return a[0] - b[0];
      }
    });
    Collections.reverse(stopLst);
    /*for (int[] item : stopLst) {
      System.out.println(Arrays.toString(item));
    }*/

    //index to keep track of where you are in ArrayList
    //dont remove items for whatever reason
    //greedy works
    long currentLocation = 0;
    long totalTastiness = 0;
    long stopIndex = 0;
    long oldIndex = -1;
    while (stopIndex < N) {
      for (long i = stopIndex; i < N; i++) {
        if (stopLst.get((int)i)[0] > currentLocation) {
          stopIndex = i;
          break;
        }
      }
      if (oldIndex == stopIndex) {
        break;
      }
      oldIndex = stopIndex;
      long lead = (fSpeed - bSpeed) * (stopLst.get((int)stopIndex)[0] - currentLocation);
      totalTastiness += lead * stopLst.get((int)stopIndex)[1];
      currentLocation = stopLst.get((int)stopIndex)[0];
      //System.out.println(totalTastiness);
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
    pw.println(totalTastiness);
    pw.close();
  }
}