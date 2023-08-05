import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("taming.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    boolean[] fixed = new boolean[N];

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("taming.out"))));
    

    for (int i = 1; i < N + 1; i++) {
      //i = num breakouts
      int minChanged = Integer.MAX_VALUE;
      int index = -1;
      //System.out.println(Arrays.toString(fixed));
      for (int j = 0; j < N; j++) {
        //j is the location to try (of the next breakout)
        if (i == 1) {
          j = 0;
        }
        int val = 0;
        int numChanged = 0;
        if (!fixed[j]) {
          for (int k = 0; k < N; k++) {
            if (fixed[k] || k == j) {
              val = 0;
            } else {
              val += 1;
            }
            if (array[k] != val) {
              numChanged += 1;
            }
          }
          if (minChanged > numChanged) {
            minChanged = numChanged;
            index = j;
          }
        }
        if (i == 1) {
          break;
        }
      }
      fixed[index] = true;
      pw.println(minChanged);
    }
    
    /*

    go through each num of breakouts
    try every combination of breakout locations, choose the best one and keep it for the next iteration

    */
   
    pw.close();
  }
}