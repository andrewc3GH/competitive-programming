import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cbarn.in"));
    int N = s.nextInt();

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt() - 1;
    }

    //System.out.println(Arrays.toString(array));

    int startingIndex = 0;
    boolean breakNow = false;
    for (startingIndex = 0; startingIndex < N; startingIndex++) {
      int cows = 0;
      for (int index = startingIndex; index < N; index++) {
        cows += array[index];
        if (cows >= 0) {
          //keep going
          if (index == N - 1) {
            breakNow = true;
          }
        } else {
          startingIndex = index + 1;
          break;
        }
      }
      if (breakNow) {
        break;
      }
    }
    //System.out.println(startingIndex);

    int[] newArray = new int[N];
    for (int i = 0; i < N; i++) {
      newArray[i] = array[(i + startingIndex) % N];
    }

    //System.out.println(Arrays.toString(newArray));

    int excessCows = 0;
    long sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < newArray[i] + 1; j++) {
        sum += (long)(j + excessCows) * (long)(j + excessCows);
      }
      excessCows += newArray[i];

    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
    pw.println(sum);
    pw.close();
    
  }
}