import java.io.*;
import java.util.*;

class Main {  
  public static int[][] array;
  public static int[] distanceArray;
  public static TreeMap<Integer, Integer> saved;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("boards.in"));
    int N = s.nextInt();
    int P = s.nextInt();
    //x, y, 1 (start) or 2 (end)
    array = new int[2 * P][4];
    //list of points
    //distance array
    //treemap (yEnd, saved value)
    for (int i = 0; i < 2 * P; i+=2) {
      array[i] = new int[]{s.nextInt(), s.nextInt(), 1, i/2};
      array[i + 1] = new int[]{s.nextInt(), s.nextInt(), 2, i/2};
    }

    //distance it takes to get to each point
    distanceArray = new int[P];
    for (int i = 0; i < P; i++) {
      distanceArray[i] = array[i*2][0] + array[i*2][1];
    }

    //sort by x
    Arrays.sort(array, (int[] a, int[] b) -> {
      if (a[0] == b[0]) {
        return a[1] - b[1];
      }
      return a[0] - b[0];
    });

    //y -> distance saved
    saved = new TreeMap<>();
    for (int i = 0; i < 2 * P; i++) {
      if (array[i][2] == 1) { //startpoint
        findDistance(i);
      } else if (array[i][2] == 2) { //endpoint
        addEndPoint(i, distanceArray[array[i][3]] - array[i][0] - array[i][1]);
      }
    }
    //System.out.println(Arrays.toString(distanceArray));

    int minDist = N + N + saved.get(saved.lastKey());
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("boards.out"))));
    pw.println(minDist);
    pw.close();
  }
  public static void findDistance(int index) {
    //System.out.println(saved);
    Integer a = saved.floorKey(array[index][1]); //ceiling at y value of index
    if (a != null) {
      //distanceArray[array[index][3]] ??
      distanceArray[array[index][3]] = saved.get(a) + array[index][0] + array[index][1]; //distance is x + y - saved
    }
  } 
  public static void addEndPoint(int index, int saveVal) {
    Integer a = saved.floorKey(array[index][1]);
    if (a != null && saveVal >= saved.get(a)) {
      return;
    }
    //System.out.println("saved: " + saved);
    a = saved.ceilingKey(array[index][1]); //floor at y value of index
    while (a != null && saved.get(a) >= saveVal) {
      saved.remove(a);
      a = saved.ceilingKey(array[index][1]);
    }
    saved.put(array[index][1], saveVal); //y, saved
    //System.out.println(array[index][1] + " " + saved);
  }
}

//array with start and end points sort by x
//go through array, startpoints -> check all valid endpoints and find new distance value
//endpoints -> remove all invalid endpoints (stored in treemap)
//find distance to last thing