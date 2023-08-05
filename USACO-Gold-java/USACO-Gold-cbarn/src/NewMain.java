import java.io.*;
import java.util.*;

class NewMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cbarn.in"));
    int N = s.nextInt();

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    
    int startingIndex = 0;
    for (startingIndex = 0; startingIndex < N; startingIndex++) {
      int cows = 0;
      boolean works = true;
      for (int i = 0; i < N; i++) {
        int index = (startingIndex + i) % N;
        cows += array[index] - 1;
        if (cows < 0) {
          startingIndex = index;
          works = false;
          break;
        }
      }
      if (works) {
        break;
      }
    }
    //System.out.println(startingIndex);

    long sum = 0;
    long extra = 0;

    for (int i = 0; i < N; i++) {
      int index = (startingIndex + i) % N;
      for (int j = 0; j < array[index]; j++) {
        sum += ((long)j + extra) * ((long)j + extra);
      }
      extra += array[index] - 1;
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
    pw.println(sum);
    pw.close();
    
  }
}