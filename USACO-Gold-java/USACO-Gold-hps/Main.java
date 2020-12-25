import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("hps.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    int[] moves = new int[N];

    for (int i = 0; i < N; i++) {
      String str = s.next();
      if (str.equals("H")) {
        moves[i] = 0;
      } else if (str.equals("P")) {
        moves[i] = 1;
      } else {
        moves[i] = 2;
      }
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 2);
    map.put(2, 1);
    map.put(1, 0);
    
    int[][][] array = new int[N][3][K + 1];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 3; j++) {
        int addWins = 0;
        if (map.get(j) == moves[i]) {
          addWins = 1;
        }
        for (int k = 0; k < K + 1; k++) {
          //array[i + 1][j][k] = Math.max(array[i + 1][j][k], array[i][j][k] + addWins);
          if (i == 0) {
            array[i][j][k] += addWins;
          } else {
            array[i][j][k] = Math.max(array[i - 1][j][k] + addWins, array[i][j][k]);
          }
          for (int l = 0; l < 3; l++) {
            if (l != j && k > 0) {
              array[i][l][k - 1] = Math.max(array[i][l][k - 1], array[i][j][k]); 
            }
          }
        }
      }
    }

    int maxVal = 0;
    for (int[] a : array[N - 1]) {
      maxVal = Math.max(a[0], maxVal);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
    pw.println(maxVal);
    pw.close();
  }
}