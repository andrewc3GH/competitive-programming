import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("div7.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    int[] prefix = new int[N];
    prefix[0] = array[0] % 7;
    for (int i = 1; i < N; i++) {
      prefix[i] = array[i] + prefix[i - 1];
      prefix[i] %= 7;
    }
    //System.out.println(Arrays.toString(prefix));
    int maxNum = 0;
    for (int i = 0; i < 7; i++){
      int low = -1;
      int high = -1;
      for (int j = 0; j < N; j++) {
        if (prefix[j] == i) {
          if (low == -1) {
            low = j;
          } else {
            high = j;
          }
        }
      }
      if (high > low) {
        maxNum = Math.max(maxNum, high - low);
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
    pw.println(maxNum);
    pw.close();
  }
}