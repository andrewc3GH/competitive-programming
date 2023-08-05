import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("visitfj.in"));
    int N = s.nextInt();
    int T = s.nextInt();
    int[][] array = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        array[i][j] = s.nextInt() + T;
      }
    }
    /*
    int[][] closestArray = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        closestArray[i][j] = 99999999;
      }
    }
    closestArray[0][0] = 0;
    */
    PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> {
      return a[2] - b[2];
    });
    //x, y, val
    queue.add(new int[]{0, 0, 0});

    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] threeSquares = new int[][]{{0, 3}, {3, 0}, {0, -3}, {-3, 0}, {1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};

    int minDist = -1;
    boolean[][] visited = new boolean[N][N];

    while (true) {
      int[] minCoor = new int[3];
      minCoor = queue.poll();
      if (!visited[minCoor[0]][minCoor[1]]) {
        visited[minCoor[0]][minCoor[1]] = true;
        if (minCoor[0] == N - 1 && minCoor[1] == N - 1) {
          minDist = minCoor[2];
          break;
        }
        for (int i = 0; i < 4; i++) {
          int[] newCoor = new int[]{minCoor[0] + directions[i][0], minCoor[1] + directions[i][1]};
          if (newCoor[0] >= 0 && newCoor[0] < N && newCoor[1] >= 0 && newCoor[1] < N && !visited[newCoor[0]][newCoor[1]]) {
            queue.add(new int[]{newCoor[0], newCoor[1], minCoor[2] + 2 * T + array[newCoor[0]][newCoor[1]]});
          }
        }
        for (int i = 0; i < 12; i++) {
          int[] newCoor = new int[]{minCoor[0] + threeSquares[i][0], minCoor[1] + threeSquares[i][1]};
          if (newCoor[0] >= 0 && newCoor[0] < N && newCoor[1] >= 0 && newCoor[1] < N && !visited[newCoor[0]][newCoor[1]]) {
            queue.add(new int[]{newCoor[0], newCoor[1], minCoor[2] + 2 * T + array[newCoor[0]][newCoor[1]]});
          }
        }
        if ((((N - 1) - minCoor[0]) + ((N - 1) - minCoor[1])) < 3) {
          queue.add(new int[]{N - 1, N - 1, minCoor[2] + (((N - 1) - minCoor[0]) + ((N - 1) - minCoor[1])) * T});
        }
      }
      
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("visitfj.out"))));
    pw.println(minDist);
    pw.close();
    
  }
}