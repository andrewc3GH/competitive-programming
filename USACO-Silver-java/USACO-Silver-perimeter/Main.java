import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("perimeter.in"));
    int N = s.nextInt();
    String[][] array = new String[N + 2][N + 2];
    for (int i = 0; i < N + 2; i++) {
      String newString = "";
      if (i != 0 && i != N + 1) {
        newString = s.next();
      }
      for (int j = 0; j < N + 2; j++) {
        if (i == 0 || j == 0 || i == N + 1 || j == N + 1) {
          array[i][j] = ".";
        } else {
          array[i][j] = String.valueOf(newString.charAt(j - 1));
        }
      }
    }
    /*
    for (String[] item : array) {
      System.out.println(Arrays.toString(item));
    }*/
    int maxArea = 0;
    int maxPerimeter = 0;
    boolean[][] visited = new boolean[N + 2][N + 2];
    for (int i = 0; i < N + 2; i++) {
      for (int j = 0; j < N + 2; j++) {
        Stack<int[]> stack = new Stack<>();
        int[] temp = {i, j};
        stack.push(temp);
        int currentArea = 0;
        int currentPerimeter = 0;
        if (visited[temp[0]][temp[1]] == false && array[temp[0]][temp[1]].equals("#")) {
          while (stack.size() > 0) {
            int[] currentCoor = stack.pop();
            if (visited[currentCoor[0]][currentCoor[1]] == false) {
              currentArea += 1;
              visited[currentCoor[0]][currentCoor[1]] = true;
              int[] copy1 = {currentCoor[0] - 1, currentCoor[1]};
              if (visited[currentCoor[0] - 1][currentCoor[1]] == false) {
                if (array[currentCoor[0] - 1][currentCoor[1]].equals(".")) {
                  currentPerimeter += 1;
                } else {
                  stack.push(copy1);
                }
              }
              int[] copy2 = {currentCoor[0] + 1, currentCoor[1]};
              if (visited[currentCoor[0] + 1][currentCoor[1]] == false) {
                if (array[currentCoor[0] + 1][currentCoor[1]].equals(".")) {
                  currentPerimeter += 1;
                } else {
                  stack.push(copy2);
                }
              }
              int[] copy3 = {currentCoor[0], currentCoor[1] - 1};
              if (visited[currentCoor[0]][currentCoor[1] - 1] == false) {
                if (array[currentCoor[0]][currentCoor[1] - 1].equals(".")) {
                  currentPerimeter += 1;
                } else {
                  stack.push(copy3);
                }
              }
              int[] copy4 = {currentCoor[0], currentCoor[1] + 1};
              if (visited[currentCoor[0]][currentCoor[1] + 1] == false) {
                if (array[currentCoor[0]][currentCoor[1] + 1].equals(".")) {
                  currentPerimeter += 1;
                } else {
                  stack.push(copy4);
                }
              }
            } 
          }
          /*
          for (boolean[] item : visited) {
            System.out.println(Arrays.toString(item));
          }*/
          if (currentArea > maxArea) {
            maxArea = currentArea;
            maxPerimeter = currentPerimeter;
          } else if (currentArea == maxArea) {
            if (currentPerimeter < maxPerimeter) {
              maxArea = currentArea;
              maxPerimeter = currentPerimeter;
            }
          }
        }
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
    pw.println(maxArea + " " + maxPerimeter);
    pw.close();
  }
}