import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cardgame.in"));
    int N = s.nextInt();
    
    boolean[] inE = new boolean[2*N];
    int[] eOneB = new int[N/2];
    int[] eOne = new int[N/2];
    int[] eTwo = new int[N/2];
    for (int i = 0; i < N; i++) {
      if (i < N/2) {
        eOneB[i] = s.nextInt();
        inE[eOneB[i] - 1] = true;
      } else {
        eTwo[i - N/2] = s.nextInt();
        inE[eTwo[i - N/2] - 1] = true;
      }
      
    }
    Arrays.sort(eOneB);
    Arrays.sort(eTwo);
    for (int i = 0; i < N/2; i++) {
      eOne[i] = eOneB[N/2 - i - 1];
    }

    //System.out.println(Arrays.toString(inE));

    int[] bArray = new int[N];
    int index = 0;
    for (int i = 0; i < 2*N; i++) {
      if (!inE[i]) {
        bArray[index] = i + 1;
        index += 1;
      }
    }

    Arrays.sort(bArray);
    //System.out.println(Arrays.toString(bArray));
    //System.out.println(Arrays.toString(eOne));
    //System.out.println(Arrays.toString(eTwo));

    boolean[] used = new boolean[N];
    int ans = 0;
    int bIndex = N - 1;
    for (int i = 0; i < N/2; i++) {
      if (bArray[bIndex] > eOne[i]) {
        ans += 1;
        used[bIndex] = true;
        bIndex -= 1;
      }
    }
    bIndex = 0;
    for (int i = 0; i < N/2; i++) {
      if (!used[bIndex] && bArray[bIndex] < eTwo[i]) {
        ans += 1;
        used[bIndex] = true;
        bIndex += 1;
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("cardgame.out"))));
    pw.println(ans);
    pw.close();
  }
}