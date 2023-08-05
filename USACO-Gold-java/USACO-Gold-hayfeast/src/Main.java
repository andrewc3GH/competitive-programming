import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    
    Scanner s = new Scanner(new File("hayfeast.in"));
    
    int N = s.nextInt();
    long M = s.nextLong();
    
    long[][] array = new long[N][2];
    //flavor, spiciness

    for (int i = 0; i < N; i++) { 
        array[i] = new long[]{s.nextLong(), s.nextLong()};
    }
    
    TreeMap<Long, Long> map = new TreeMap<Long, Long>(); 
    long flavor = 0;
    long minSpicy = 999999999;
    int lowIndex = 0;

    for (int i = 0; i < N; i++) {
        flavor += array[i][0];
        if (!map.containsKey(array[i][1])) {
          map.put(array[i][1], (long)1);
        } else {
          map.put(array[i][1], map.get(array[i][1]) + 1);
        }
        while (flavor >= M) {
            minSpicy = Math.min(minSpicy, map.lastKey());
            map.put(array[lowIndex][1], map.get(array[lowIndex][1]) - 1);
            if (map.get(array[lowIndex][1]) == 0) {
              map.remove(array[lowIndex][1]);
            }
            flavor -= array[lowIndex][0];
            lowIndex += 1;
        }
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
    pw.println(minSpicy);
    pw.close();
  }
}