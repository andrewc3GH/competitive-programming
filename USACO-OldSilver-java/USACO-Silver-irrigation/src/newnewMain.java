import java.io.*;
import java.util.*;

class newnewMain {  
  
  /*
  use a 1D array for UF
  Assign an integer to each point (multiply x-coordinate by 1000 and add y-coordinate or just assign it based on order)

  Use primms instead
  */

  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("irrigation.in"));
    int N = s.nextInt();
    int C = s.nextInt();

    int[][] array = new int[N][2];

    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    int[] closest = new int[N];
    for (int i = 0; i < N; i++) {
      closest[i] = 999999999;
    }
    closest[0] = C;

    boolean[] visited = new boolean[N];
    int sum = -1 * C;
    for (int i = 0; i < N; i++) {
      int minVal = 999999999;
      int minIndex = -1;
      //System.out.println(Arrays.toString(closest) + Arrays.toString(visited));
      for (int j = 0; j < N; j++) {
        if (!visited[j] && closest[j] < minVal && closest[j] >= C) {
          minVal = closest[j];
          minIndex = j;
        }
      }
      visited[minIndex] = true;
      sum += minVal;

      for (int j = 0; j < N; j++) {
        if ((array[minIndex][0] - array[j][0]) * (array[minIndex][0] - array[j][0]) + (array[minIndex][1] - array[j][1]) * (array[minIndex][1] - array[j][1]) >= C) {
          closest[j] = Math.min(closest[j], (array[minIndex][0] - array[j][0]) * (array[minIndex][0] - array[j][0]) + (array[minIndex][1] - array[j][1]) * (array[minIndex][1] - array[j][1]));
        }
      }
    }
   

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("irrigation.out"))));
    pw.println(sum);
    pw.close();
  }
}