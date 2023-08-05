import java.io.*;
import java.util.*;

class newMain {  

  /* try combining smallest edges
  start with N clusters
  combine clusters until K clusters remain
  use union find to combine clusters/check if two things are connected
  
  primms without a priority queue

  dont precompute everything
  instead every time u are on a new node, compute how far everything is from that node when u get there
  */

  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("walk.in"));
    int N = s.nextInt();
    int K = s.nextInt();

    int minIndex = -1;
    long min = 999999999999999999L;
    for (int i = 1; i < N + 1; i++) {
      for (int j = i + 1; j < N + 1; j++) {
        long val = ((long)2019201913 * (long)i + (long)2019201949 * (long)j) % (long)2019201997;
        if (val < min) {
          min = val;
          minIndex = i;
        }
      }
    }

    /*
    long[][] array = new long[N + 1][N + 1];
    long min = 999999999999999999L;
    int minIndex = -1;
    for (int i = 1; i < N + 1; i++) {
      for (int j = i + 1; j < N + 1; j++) {
        long val = ((long)2019201913 * (long)i + (long)2019201949 * (long)j) % (long)2019201997;
        array[i][j] = val;
        array[j][i] = val;
        if (val < min) {
          min = val;
          minIndex = i;
        }
      }
    }
    */

    boolean[] visited = new boolean[N + 1];
    long[] closest = new long[N + 1];
    for (int i = 1; i < N + 1; i++) {
      closest[i] = 999999999999999999L;
    }
    closest[minIndex] = 0;
    /*
    for (int j = 1; j < N + 1; j++) {
      closest[j] = Math.min(closest[j], array[minIndex][j]);
    }
    */

    for (int i = 0; i < N - K + 1; i++) {
      long minDistance = 999999999999999999L;
      int currentIndex = -1;
      for (int j = 1; j < N + 1; j++) {
        if (minDistance > closest[j] && !visited[j]) {
          minDistance = closest[j];
          currentIndex = j;
        }
      }
      //System.out.println(currentIndex + " " + Arrays.toString(closest));
      visited[currentIndex] = true;
      for (int j = 1; j < N + 1; j++) {
        closest[j] = Math.min(closest[j], ((long)2019201913 * (long)j + (long)2019201949 * (long)currentIndex) % (long)2019201997);
      }
      //System.out.println(currentIndex + " " + Arrays.toString(closest));
    }
    
    long finalMin = 999999999999999999L;
    int finalIndex = -1;
    for (int j = 1; j < N + 1; j++) {
      if (finalMin > closest[j] && !visited[j]) {
        finalMin = closest[j];
        finalIndex = j;
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("walk.out"))));
    pw.println(closest[finalIndex]);
    pw.close();
  }
}