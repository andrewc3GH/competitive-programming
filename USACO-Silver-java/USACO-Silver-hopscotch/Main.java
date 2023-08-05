import java.io.*;
import java.util.*;

//still thinking of solution but N^4 might work

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("hopscotch.in"));
    int R = s.nextInt();
    int C = s.nextInt();
    int K = s.nextInt();

    int[][] array = new int[R][C];
    int[][] values = new int[R][C];
    
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        array[i][j] = s.nextInt();
      }
      //System.out.println(Arrays.toString(array[i]));
    }

    values[0][0] = 1;

    for (int i = 0; i < R - 1; i++) {
      for (int j = 0; j < C - 1; j++) {
        for (int k = i + 1; k < R; k++) {
          for (int l = j + 1; l < C; l++) {
            if (array[i][j] != array[k][l]) {
              values[k][l] += values[i][j];
              values[k][l] %= 1000000007;
            }
          }
        }
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("hopscotch.out"))));
    pw.println(values[R - 1][C - 1]);
    pw.close();
  }
}