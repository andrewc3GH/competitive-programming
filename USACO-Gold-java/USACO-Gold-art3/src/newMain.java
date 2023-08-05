import java.io.*;
import java.util.*;

class newMain {  

  //maybe try where i = startindex and j = endindex
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("art3.in"));
    //Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    //length of interval, starting Index
    int[][] dpArray = new int[N][N];
    
    for (int i = 0; i < N; i++) {
      dpArray[0][i] = 0;
    }

    /*
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(dpArray[i]));
    }
    */

    //intervalsize = i + 1
    for (int i = 1; i < N; i++) {
      //j = starting index
      for (int j = 0; j < N - i; j++) {
        int maxVal = 0;
        int left = 0;
        int right = i - 1;
        for (int k = j + 1; k <= j + i; k++) { //something wrong here not getting 1 + 1 = 2 program is adding 1 + 0
          int intervalLeft = dpArray[left][j];
          int intervalRight = dpArray[right][k];
          //System.out.println(i + " " + j + " " + k);
          maxVal = Math.max(maxVal, intervalLeft + intervalRight);
          //System.out.println(intervalLeft + " " +  intervalRight);
          left += 1;
          right -= 1;
        }
        if (array[j] == array[j + i] && i == 1) { // <--
          //System.out.println(i + " " + j);
          maxVal = Math.max(maxVal, 1);
        } else if (array[j] == array[j + i]) { // <--
          //System.out.println(i + " " + j);
          maxVal = Math.max(maxVal, dpArray[i - 2][j + 1] + 1);
        }
        dpArray[i][j] = maxVal;
      }
    }
    
    for (int i = 0; i < N; i++) {
      //System.out.println((i + 1) + " " + Arrays.toString(dpArray[i]));
    }
    
    System.out.println(N - dpArray[N - 1][0]);
    
  }
}

/*

1 2 3 4 1 4 3 2 1 6
 2 2 2 2 2 2 2 2 2
  3 3 3 2 3 3 3 3
   4 4 3 3 4 4 4
    

1 2 3 4 1 6 3 2 1 4
1 2 3 4 4 5 5 5 5 6

either paint from any previous same number, or paint new stroke

either paint just the individual square or any of the same color to the right
fenwick tree to count how much is being painted over

1 2 3 4 1 4 3 2 1 6
1 1 1 1 1 0 0 0 0 0
1 2 2 2 2 2 2 2 0 0
1 2 3 3 3 3 3 2 0 0
1 2 3 4 4 4 3 2 0 0
1 2 3 4 1 4 3 2 0 0
1 2 3 4 1 4 3 2 1 0
1 2 3 4 1 4 3 2 1 6



1 2 3 4 1 6 3 2 1 4
1 1 1 1 1
1 2 2 2 2 2 2 2
1 2 3 3 3 3 3 2
1 2 3 4 3 3 3 2
1 2 3 4 1 3 3 2
1 2 3 4 1 6 3 2 
1 2 3 4 1 6 3 2 1 4


10
1 2 3 4 1 4 3 2 1 6
1 1 1 1 1 1 1 1 1 0
1 2 2 2 2 2 2 2 1 0
1 2 3 3 3 3 3 2 1 0
1 2 3 4 4 4 3 2 1 0
1 2 3 4 1 4 3 2 1 0
1 2 3 4 1 4 3 2 1 6

*/