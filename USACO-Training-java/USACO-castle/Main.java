/*
ID: cruzan1
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.*;

class castle {
  public static int[][] moduleLst;
  public static int M;
  public static int N;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("castle.in"));
    M = s.nextInt();
    N = s.nextInt();
    moduleLst = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        moduleLst[i][j] = s.nextInt();
      }
      System.out.println(Arrays.toString(moduleLst[i]));
    }
    boolean[][] visited = new boolean[N][M];
    ArrayList<Integer> sizeLst = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!visited[i][j]) {
          sizeLst.add(floodFill(i, j, visited));
        }
      }
    }
    Collections.sort(sizeLst);
    System.out.println(sizeLst);
    //now remove one wall at at time and call the adjacent module. Remove the wall by checking if there is a wall there and subtracting by the corresponding amount. Then create a new visited array and run floodfill to see how large the ne

    int largestRoom = 0;
    int[] roomCoor = new int[2];
    String direction = "";
    for(int j = 0; j < M; j++) {
      for (int i = N - 1; i > -1; i--) {
        int wallNum = moduleLst[i][j];
        if (i + 1 == 3 && j + 1 == 1) {
          System.out.println("3, 1");
        }
        visited = new boolean[N][M];
        if (i > 0 && (wallNum & (1 << 1)) != 0) {
          int currentRoom = floodFill(i, j, visited) + floodFill(i - 1, j, visited);
          if (currentRoom > largestRoom) {
            largestRoom = currentRoom;
            roomCoor[0] = i + 1;
            roomCoor[1] = j + 1;
            direction = "N";
          }
        } 
        visited = new boolean[N][M];
        if (j < M - 1 && (wallNum & (1 << 2)) != 0) {
          int currentRoom = floodFill(i, j, visited) + floodFill(i, j + 1, visited);
          if (currentRoom > largestRoom) {
            largestRoom = currentRoom;
            roomCoor[0] = i + 1;
            roomCoor[1] = j + 1;
            direction = "E";
          }
        } 
      }
    }
    System.out.println(direction);

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
    pw.println(sizeLst.size());
    pw.println(sizeLst.get(sizeLst.size() - 1));
    pw.println(largestRoom);
    pw.println(roomCoor[0] + " " + roomCoor[1] + " " + direction);
    pw.close();

  }
  public static int floodFill(int y, int x, boolean[][] visited) {
    if (y >= N || y < 0 || x >= M || x < 0 || visited[y][x]) {
      return 0;
    }
    visited[y][x] = true;
    int size = 1;
    if ((moduleLst[y][x] & (1 << 0)) == 0) {
      size += floodFill(y, x - 1, visited);
    }
    if ((moduleLst[y][x] & (1 << 1)) == 0) {
      size += floodFill(y - 1, x, visited);
    }
    if ((moduleLst[y][x] & (1 << 2)) == 0) {
      size += floodFill(y, x + 1, visited);
    }
    if ((moduleLst[y][x] & (1 << 3)) == 0) {
      size += floodFill(y + 1, x, visited);
    }
    return size;
  }
}