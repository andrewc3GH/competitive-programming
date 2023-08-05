import java.io.*;
import java.util.*;

/*
5 3
101
85
100
46
95
*/

/*

5 2
5
6
7
8
500

*/

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int K = s.nextInt();

    int[] array = new int[N + 1];
    array[0] = 0;
    for (int i = 0; i < N; i++) {
      array[i + 1] = s.nextInt();
    }
    Arrays.sort(array);
    //System.out.println(Arrays.toString(array));

    int totalYears =(int) (Math.ceil((double)array[N]/12) - Math.floor((double)array[0]/12)) * 12;
    //System.out.println(totalYears);

    int[] differenceArray = new int[N];
    for (int i = 1; i < N + 1; i++) {
      differenceArray[i - 1] = (int) (Math.floor((double)array[i]/12) - Math.ceil((double)array[i - 1]/12)) * 12;
    }
    Arrays.sort(differenceArray);
    //System.out.println(Arrays.toString(differenceArray));
    int counter = 0;
    for (int i = N - 1; i > -1; i--) {
      if (counter < K - 1 && differenceArray[i] > 0) {
        counter += 1;
        totalYears -= differenceArray[i];
      } else {
        break;
      }
    }
    System.out.println(totalYears);
  }
}
