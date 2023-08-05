import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("hps.in"));
    int N = s.nextInt();
    String[] moveArray = new String[N];
    for (int i = 0; i < N; i++) {
      moveArray[i] = s.next();
    }
    System.out.println(Arrays.toString(moveArray));
    int[] Harray = new int[N];
    int[] Parray = new int[N];
    int[] Sarray = new int[N];
    for (int i = 0; i < N; i++) {
      if (moveArray[i].equals("H") && i != 0) {
        Harray[i] = Harray[i - 1] + 1;
        Parray[i] = Parray[i - 1];
        Sarray[i] = Sarray[i - 1];
      } else if (moveArray[i].equals("P") && i != 0) {
        Harray[i] = Harray[i - 1];
        Parray[i] = Parray[i - 1] + 1;
        Sarray[i] = Sarray[i - 1];
      } else if (moveArray[i].equals("S") && i != 0) {
        Harray[i] = Harray[i - 1];
        Parray[i] = Parray[i - 1];
        Sarray[i] = Sarray[i - 1] + 1;
      } else if (moveArray[i].equals("H")) {
        Harray[i] = 1;
        Parray[i] = 0;
        Sarray[i] = 0;
      } else if (moveArray[i].equals("P")) {
        Harray[i] = 0;
        Parray[i] = 1;
        Sarray[i] = 0;
      } else if (moveArray[i].equals("S")) {
        Harray[i] = 0;
        Parray[i] = 0;
        Sarray[i] = 1;
      }
    }
    System.out.println(Arrays.toString(Harray));
    System.out.println(Arrays.toString(Parray));
    System.out.println(Arrays.toString(Sarray));
    int maxSum = 0;
    for (int i = 0; i < N - 1; i++) {
      int left = 0;
      int right = 0;
      if (i == 0) {
        left = 1;
      }
      left = Math.max(Harray[i], Parray[i]);
      left = Math.max(left, Sarray[i]);
      right = Math.max(Harray[N - 1] - Harray[i], Parray[N - 1] - Parray[i]);
      right = Math.max(right, Sarray[N - 1] - Sarray[i]);
      if (left + right > maxSum) {
        maxSum = left + right;
      }
    }
    System.out.println(maxSum);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
    pw.println(maxSum);
    pw.close();
  }
}