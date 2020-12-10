import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("angry.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    Arrays.sort(array);

    int maxNum = array[array.length - 1] - array[0];
    int minNum = 0;
    int oldNum = 0;
    int currentNum = -1;
    int lastNumThatWorks = -1;
    while (oldNum != currentNum) {
      oldNum = currentNum;
      currentNum = (maxNum + minNum)/2;
      int totalUsed = 0;
      int currentPlacement = array[0];
      for (int i = 0; i < N; i++) {
        if (currentPlacement + currentNum * 2 < array[i]) {
          currentPlacement = array[i];
          totalUsed += 1;
        }
      }
      if (totalUsed >= K) {
        minNum = currentNum;
      } else {
        maxNum = currentNum;
        lastNumThatWorks = currentNum;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
    pw.println(lastNumThatWorks);
    pw.close();
  }
}