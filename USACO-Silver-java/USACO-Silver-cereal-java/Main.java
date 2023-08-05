import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cereal.in"));
    int N = s.nextInt();
    int numCereals = s.nextInt();

    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    int[] cereals = new int[numCereals];

    for (int i = 0; i < N; i++) {
      int firstChoice = array[i][0] - 1;
      int secondChoice = array[i][1] - 1;
      if (cereals[secondChoice] < cereals[firstChoice]) {
        cereals[secondChoice] = cereals[firstChoice];
      }
      cereals[firstChoice] = i + 1;
    }

    int start = 0;
    int[] remove = new int[N];
    for (int i = 0; i < numCereals; i++) {
      if (cereals[i] != 0) {
        remove[cereals[i] - 1] = -1;
        start += 1;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
    for (int i = 0; i < N; i++) {
      pw.println(start);
      if (remove[i] == -1) {
        start -= 1;
      }
    }
    pw.close();
  }
}