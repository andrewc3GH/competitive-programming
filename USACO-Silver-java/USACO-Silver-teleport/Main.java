import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("teleport.in"));
    int N = s.nextInt();
    int[][] array = new int[N][2];
    long initialHeight = 0;
    for (int i = 0; i < N; i++) {
      array[i][0] = s.nextInt();
      array[i][1] = s.nextInt();
      initialHeight += (long)Math.abs(array[i][0] - array[i][1]);
    }
    s.close();
    /*
    for (int[] item : array) {
      System.out.println(Arrays.toString(item));
    }
    System.out.println();
    */
    ArrayList<int[]> events = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if ((long)array[i][0] * (long)array[i][1] < (long)0) {
        int[] temp2 = new int[2];
        temp2[0] = array[i][1];
        temp2[1] = 2;
        events.add(temp2);
        int[] temp3 = new int[2];
        temp3[0] = array[i][1] * 2;
        temp3[1] = -1;
        events.add(temp3);
        int[] temp4 = new int[2];
        temp4[0] = 0;
        temp4[1] = -1;
        events.add(temp4);
      } else {
        if (Math.abs(array[i][0] * 2) < Math.abs(array[i][1])) {
          int[] temp = new int[2];
          temp[0] = array[i][0] * 2;
          temp[1] = -1;
          events.add(temp);
          int[] temp1 = new int[2];
          temp1[0] = array[i][1];
          temp1[1] = 2;
          events.add(temp1);
          int[] temp2 = new int[2];
          temp2[0] = array[i][1] + (array[i][1] - array[i][0] * 2);
          temp2[1] = -1;
          events.add(temp2);
        }
      } 
    }
    Collections.sort(events, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });
    /*
    for (int[] item : events) {
      System.out.println(Arrays.toString(item));
    }
    System.out.println(initialHeight);
    */
    System.out.println(events.size());
    long slope = (long)0;
    long position = (long)Math.pow(10, 8) * -1;
    long currentHeight = (long)initialHeight;
    long minHeight = (long)initialHeight;
    for (int i = 0; i < events.size(); i++) {
      long newPosition = (long)events.get(i)[0];
      currentHeight += (long)Math.abs(position - newPosition) * slope;
      slope += (long)events.get(i)[1];
      position = (long)newPosition;
      if (currentHeight < minHeight) {
        minHeight = (long)currentHeight;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
    pw.println(minHeight);
    pw.close();

    /* sort events by location
    go through every event and compute slope/height
    check if height is less than any other height
    return lowest height
    */

    //for sleepy cow herding, use sliding window to find interval of length equal to the number cows that contains most cows. then move every other cow into the space (num cows that remain is the answer for min)
    //for the max, find the move that decreases the range of all the cows by the least

    //for the paintbarn problem, list of events - when rectangles start/end, at each event calculate the number of overlapping rectangles
  }
}

/*
1, 10  2 - 18
5, 15  10 - 20
9, 12  

to check if its useful, make sure the lower number times two is not bigger than the bigger number (after abs).
*/


/*
5, 12
10: 7
11: 6
12: 5
13: 6
14: 7

4, 11
8: 7
9: 6
10: 5
11: 4
12: 5
13: 6
14: 7
*/