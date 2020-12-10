import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("paintbarn.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    int[][] paintArray = new int[N][4];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 4; j++) {
        paintArray[i][j] = s.nextInt();
      }
    }
    int highestX = 0;
    int highestY = 0;
    //start x, bottom y, end x, top y
    ArrayList<int[]> eventLst = new ArrayList<>();
    for (int[] item : paintArray) {
      int[] addEvent = new int[4];
      addEvent[0] = item[0];
      addEvent[1] = item[1];
      addEvent[2] = item[3];
      addEvent[3] = 1;
      int[] minusEvent = new int[4];
      minusEvent[0] = item[2];
      minusEvent[1] = item[1];
      minusEvent[2] = item[3];
      minusEvent[3] = -1;
      eventLst.add(addEvent);
      eventLst.add(minusEvent);
      if (item[2] > highestX) {
        highestX = item[2];
      } if (item[3] > highestY) {
        highestY = item[3];
      }
    }

    Collections.sort(eventLst, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });
    /*
    for (int[] item : eventLst) {
      System.out.println(Arrays.toString(item));
    }
    */
    //start/end, bottom y, top y, layer difference
    int[][] paintLayers = new int[highestY + 2][highestX + 2];
    for (int[] item : eventLst) {
      for (int i = item[1]; i < item[2]; i++) {
        paintLayers[i][item[0]] += item[3];
      }
    }
    /*
    for (int[] item : paintLayers) {
      System.out.println(Arrays.toString(item));
    }
    */
    int[] columnValues = new int[highestY + 2];
    int numOkay = 0;
    for (int i = 0; i < highestX + 2; i++) {
      for (int j = 0; j < highestY + 2; j++) {
        columnValues[j] += paintLayers[j][i];
        if (columnValues[j] == K) {
          numOkay += 1;
        }
      }
      
      //System.out.println(Arrays.toString(columnValues));
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
    pw.println(numOkay);
    pw.close();

    //System.out.println(Arrays.toString(columnValues));
  }
}

/*
for paintbarn, (pretend N is 1000)
create list of x-events (when to add y-interval of paint)
sort the list of x-events
loop through the list of x-events
maintain the number of layers of paint on each square on the current column (up - down)
update the answer appropriately
(should get about half the test cases)
(january 2019 too)
*/