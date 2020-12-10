import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("mountains.in"));
    int N = s.nextInt();
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i][0] = s.nextInt();
      array[i][1] = s.nextInt();
    }
    Arrays.sort(array, (int[] a, int[] b) -> {
      if (a[0] - a[1] == b[0] - b[1]) {
        return (b[0] + b[1]) - (a[0] + a[1]);
      } else {
        return (a[0] - a[1]) - (b[0] - b[1]);
      }
    });
    int previous = -10000000;
    int counter = 0;
    for (int i = 0; i < N; i++) {
      if (array[i][0] + array[i][1] > previous) {
        counter += 1;
        previous = array[i][0] + array[i][1];
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
    pw.println(counter);
    pw.close();
  }
}

/*
assign each point a new value which is equal to x + y
sort the points by (x - y) and add the points that have a greater (x + y) value than the previous.
(rotate the orignal graph by 45 degrees)
*/