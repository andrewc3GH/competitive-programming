import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("countcross.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    int R = s.nextInt();

    boolean[][][] mapArray = new boolean[N][N][4];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        boolean[] temp = {true, true, true, true};
        int[] temporary = new int[2];
        temporary[0] = i + 1;
        temporary[1] = j + 1;
        if (j == 0) {
          temp[0] = false;
        }
        if (j == N - 1) {
          temp[2] = false;
        }
        if (i == 0) {
          temp[1] = false;
        }
        if (i == N - 1) {
          temp[3] = false;
        }
        mapArray[i][j] = temp;
      }
    }

    for (int i = 0; i < R; i ++) {
      int r1 = s.nextInt() - 1; //r1
      int c1 = s.nextInt() - 1; //c1
      int r2 = s.nextInt() - 1; //r2
      int c2 = s.nextInt() - 1; //c2
      if (c2 < c1) {
        mapArray[r1][c1][0] = false;
        mapArray[r2][c2][2] = false;
      }
      if (c2 > c1) {
        mapArray[r1][c1][2] = false;
        mapArray[r2][c2][0] = false;
      }
      if (r2 < r1) {
        mapArray[r1][c1][1] = false;
        mapArray[r2][c2][3] = false;
      }
      if (r2 > r1) {
        mapArray[r1][c1][3] = false;
        mapArray[r2][c2][1] = false;
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.println(Arrays.toString(mapArray[i][j]));
      }
    }
    int[][] cowArray = new int[K][2];
    for (int i = 0; i < K; i ++) {
      int[] temp = new int[2];
      for (int j = 0; j < 2; j++) {
        temp[j] = s.nextInt();
      }
      cowArray[i] = temp;
    }

    //[left, up, right, down]
    /*
    boolean[][][] mapArray = new boolean[N][N][4];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        boolean[] temp = {true, true, true, true};
        int[] temporary = new int[2];
        temporary[0] = i + 1;
        temporary[1] = j + 1;
        if (i == 0) {
          temp[0] = false;
        }
        if (i == N - 1) {
          temp[2] = false;
        }
        if (j == 0) {
          temp[1] = false;
        }
        if (j == N - 1) {
          temp[3] = false;
        }
        for (int[] key : roadMap.keySet()) {
          if (Arrays.toString(key).equals(Arrays.toString(temporary))) {
            if (roadMap.get(key)[0] < key[0]) {
              temp[0] = false;
            }
            if (roadMap.get(key)[0] > key[0]) {
              temp[2] = false;
            }
            if (roadMap.get(key)[1] < key[1]) {
              temp[1] = false;
            }
            if (roadMap.get(key)[1] > key[1]) {
              temp[3] = false;
            }
          }
        }
        mapArray[i][j] = temp;
      }
    }
    */
    /*System.out.println(Arrays.toString(mapArray[0][0]));
    System.out.println(Arrays.toString(mapArray[0][1]));
    System.out.println(Arrays.toString(mapArray[0][2]));
    System.out.println(Arrays.toString(mapArray[1][0]));
    System.out.println(Arrays.toString(mapArray[1][1]));
    System.out.println(Arrays.toString(mapArray[1][2]));
    System.out.println(Arrays.toString(mapArray[2][0]));
    System.out.println(Arrays.toString(mapArray[2][1]));
    System.out.println(Arrays.toString(mapArray[2][2]));*/
    int[][] painting = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        painting[i][j] = 0;
      }
    }
    int counter = 1;
    for (int i = 0; i < K; i++) {
      floodFill(painting, cowArray[i][0] - 1, cowArray[i][1] - 1, mapArray, 0, counter);
      counter += 1;
    }

    HashMap<Integer, Integer> colorMap = new HashMap();
    for(int i = 0; i < K; i++) {
      if (colorMap.containsKey(painting[cowArray[i][0] - 1][cowArray[i][1] - 1])) {
        colorMap.put(painting[cowArray[i][0] - 1][cowArray[i][1] - 1], colorMap.get(painting[cowArray[i][0] - 1][cowArray[i][1] - 1]) + 1);
      } else {
        colorMap.put(painting[cowArray[i][0] - 1][cowArray[i][1] - 1], 1);
      }
    }

    int[] colorArray = new int[colorMap.size()];
    int newCounter = 0;
    for (int key : colorMap.keySet()) {
      colorArray[newCounter] = colorMap.get(key);
      newCounter += 1;
    }

    int newNewCounter = 0;
    for (int i = 0; i < colorArray.length; i++) {
      newNewCounter += colorArray[i] * (K - colorArray[i]);
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
    pw.println(newNewCounter/2);
    pw.close();
  }

  public static void floodFill(int[][] grid, int x, int y, boolean[][][] mapArray, int oldColor, int newColor) {
    if (grid[x][y] == oldColor) {
      grid[x][y] = newColor;
    }
    if (mapArray[x][y][0] == true && grid[x][y - 1] == oldColor) {
      grid[x][y - 1] = newColor;
      floodFill(grid, x, y - 1, mapArray, oldColor, newColor);
    } 
    if (mapArray[x][y][1] == true && grid[x - 1][y] == oldColor) {
      grid[x - 1][y] = newColor;
      floodFill(grid, x - 1, y, mapArray, oldColor, newColor);
    }
    if (mapArray[x][y][2] == true && grid[x][y + 1] == oldColor) {
      grid[x][y + 1] = newColor;
      floodFill(grid, x, y + 1, mapArray, oldColor, newColor);
    } 
    if (mapArray[x][y][3] == true && grid[x + 1][y] == oldColor) {
      grid[x + 1][y] = newColor;
      floodFill(grid, x + 1, y, mapArray, oldColor, newColor);
    } 
  }
}