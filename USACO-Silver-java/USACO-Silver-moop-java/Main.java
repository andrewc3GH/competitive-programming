import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("moop.in"));
    int N = s.nextInt();
    
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), s.nextInt()};
    }
    Arrays.sort(array, (int[] a, int[] b) -> {
      if (a[0] > b[0]) {
        return 1;
      } else if (a[0] == b[0]) {
        return a[1] - b[1];
      } else {
        return -1;
      }
    });

    /*
    for (int[] item : array) {
      System.out.print(Arrays.toString(item) + " ");
    }
    System.out.println();*/

    int[] y = new int[N];
    for (int i = 0; i < N; i++) {
      y[i] = array[i][1];
    }

    //System.out.println(Arrays.toString(y));

    //System.out.println("_____________________________");
    int[] arr = new int[N];
    arr[0] = y[0];
    int currentIndex = 0;
    for (int i = 1; i < N; i++) {
      if (y[i] < arr[currentIndex]) {
        currentIndex += 1;
        arr[currentIndex] = y[i];
      } else {
        boolean runs = false;
        int min = arr[currentIndex];
        for (int j = currentIndex - 1; j > -1; j--) {
          if (y[i] >= arr[j]) {
            currentIndex -= 1;
            runs = true;
          } else {
            break;
          }
        }
        arr[currentIndex] = min;
      }
      //System.out.println(Arrays.toString(arr));
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
    pw.println(currentIndex + 1);
    pw.close();
  }
}