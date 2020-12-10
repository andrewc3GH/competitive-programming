/*
ID: cruzan1
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.*;

class hamming {
  public static void main(String[] args) throws IOException {
    // read in input
    Scanner s = new Scanner(new File("hamming.in"));
    int N = s.nextInt();
    int B = s.nextInt();
    int D = s.nextInt();
    int numCodes = 0;
    int currentNum = 0;
    ArrayList<Integer> validCodes = new ArrayList<>();
    while (numCodes < N) {
      boolean works = true;
      for (int element : validCodes) {
        if (check(currentNum, element, B, D) == true) {
          works = false;
          break;
        }
      }
      if (works == true) {
        validCodes.add(currentNum);
        numCodes += 1;
      }
      currentNum += 1;
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
    for (int i = 0; i < numCodes; i++) {
      if ((i + 1) % 10 == 0 || i == numCodes - 1) {
        pw.println(validCodes.get(i));
      } else {
        pw.print(validCodes.get(i) + " ");
      }
    }
    pw.close();
  }
  public static boolean check(int a, int c, int B, int D) {
    int counter = 0;
    for (int i = 0; i < B + 1; i++) {
      if ((a & (1 << i)) != (c & (1 << i))) {
        counter += 1;
      }
    }
    if (counter >= D) {
      return false;
    }
    return true;
  }
}