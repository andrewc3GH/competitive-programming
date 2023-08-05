import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("snowboots.in"));
    int N = s.nextInt();
    int B = s.nextInt();

    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      array[i] = new int[]{s.nextInt(), i};
    }
    //Arrays.sort(array);
    Arrays.sort(array, (int[] a, int[] b) -> {
       if (a[0] == b[0]) {
           return a[1] - b[1];
       } 
       return a[0] - b[0];
    });
    
    // [0, 1, 2, 3, 8, 7, 6, 0]
    //  0  1  2  3  4  5  6  7
    //  0  7  1  2  3  6  5  4
    
    //index
    TreeSet<Integer> map = new TreeSet<>();
    map.add(0);
    map.add(N - 1);
    
    //snow depth, min len needed to pass
    TreeMap<Integer, Integer> ans = new TreeMap<>();
    ans.put(0, N - 1);
    
    //interval len, quantity
    TreeMap<Integer, Integer> intervals = new TreeMap<>();
    intervals.put(N - 1, 1);
    //System.out.println(intervals);
    
    for (int i = 0; i < N; i++) {
      int snowVal = array[i][0];
      int index = array[i][1];
      if (index != 0 && index != N - 1) {
        map.add(index);
        int intervalTop = map.ceiling(index + 1) - map.floor(index);
        int intervalBot = map.ceiling(index) - map.floor(index - 1);
        //System.out.println(map + " " + intervalTop + " " + intervalBot);
        intervals.put(intervalTop + intervalBot, intervals.get(intervalTop + intervalBot) - 1);
        if (intervals.get(intervalTop + intervalBot) == 0) {
            intervals.remove(intervalTop + intervalBot);
        }
        if (!intervals.containsKey(intervalTop)) {
            intervals.put(intervalTop, 0);
        } if (!intervals.containsKey(intervalBot)) {
            intervals.put(intervalBot, 0);
        }
        intervals.put(intervalTop, intervals.get(intervalTop) + 1);
        intervals.put(intervalBot, intervals.get(intervalBot) + 1);
        //System.out.println(intervals.lastKey());
        ans.put(snowVal, intervals.lastKey());
      }
    }
    //System.out.println(ans);

    int[][] bArray = new int[N][2];
    for (int i = 0; i < B; i++) {
      bArray[i] = new int[]{s.nextInt(), s.nextInt()};
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("snowboots.out"))));
    for (int i = 0; i < B; i++) {
      //System.out.println(ans.get(ans.floorKey(bArray[i][0])) + " " + bArray[i][1]);
      if (ans.get(ans.floorKey(bArray[i][0])) <= bArray[i][1]) {
        pw.println(1);
      } else {
        pw.println(0);
      }
    }
    pw.close();
  }
}