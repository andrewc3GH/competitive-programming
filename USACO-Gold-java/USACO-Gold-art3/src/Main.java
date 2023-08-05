import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    //Scanner s = new Scanner(new File("art3.in"));
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    int[] newArray = new int[N];
    int[] strokesArray = new int[N];
    int strokeNum = 1;

    int numStrokes = 0;
    for (int i = 0; i < N; i++) {
      int color = array[i];
      if (newArray[i] != color) {
        newArray[i] = color;
        numStrokes += 1;
        strokesArray[i] = strokeNum;
      }
      int efficiency = 0;
      int lastEfficiency = -1;
      int newIndex = i;
      boolean[] seenStrokesN = new boolean[strokeNum];
      boolean[] seenStrokesP = new boolean[strokeNum];
      for (int j = i + 1; j < N; j++) {
        if (newArray[j] == array[j] && newArray[j] != color && newArray[j] != newArray[j - 1]) {
          efficiency -= 1;
          seenStrokesN[strokesArray[j]] = true;
        } if (newArray[j] != color && array[j] == color && newArray[j] != newArray[j - 1]) {
          efficiency += 1;
          seenStrokesP[strokesArray[j]] = true;
        } if (array[j] == color) {
          if (efficiency >= 0) {
            lastEfficiency = efficiency;
            newIndex = j;
          }
        }
      }
      if (lastEfficiency >= 0) {
        for (int j = i; j < newIndex + 1; j++) {
          newArray[j] = color;
          strokesArray[j] = strokeNum;
        }
        strokeNum += 1;
      }
      //System.out.println(Arrays.toString(newArray) + " " + numStrokes + " " + lastEfficiency);
    }
    
    //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("art3.out"))));
    System.out.println(numStrokes);
    //pw.close();
    
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