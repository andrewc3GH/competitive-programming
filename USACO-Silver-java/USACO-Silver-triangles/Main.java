import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("triangles.in"));
    int N = s.nextInt();
    ArrayList[] array = new ArrayList[N];
    HashMap<ArrayList<Integer>, int[]> valueMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      array[i] = new ArrayList<>();
      array[i].add(s.nextInt());
      array[i].add(s.nextInt());
      valueMap.put(array[i], new int[2]);
    }

    Arrays.sort(array, (ArrayList a, ArrayList b) -> {
      if (a.get(0) != b.get(0)) {
        return (int)a.get(0) - (int)b.get(0);
      } else {
        return (int)a.get(1) - (int)b.get(1);
      }
    });

    HashMap<Integer, ArrayList<ArrayList<Integer>>> sameX = new HashMap<>();
    HashMap<Integer, ArrayList<ArrayList<Integer>>> sameY = new HashMap<>();
    for (ArrayList coor : array) {
      if (!sameX.containsKey(coor.get(0))) {
        sameX.put((int)coor.get(0), new ArrayList<>());
      } if (!sameY.containsKey(coor.get(1))) {
        sameY.put((int)coor.get(1), new ArrayList<>());
      }
      sameX.get(coor.get(0)).add(coor);
      sameY.get(coor.get(1)).add(coor);
    }

    for (int xCoor : sameX.keySet()) {
      ArrayList<ArrayList<Integer>> currentLst = sameX.get(xCoor);
      int baseSum = 0;
      for (ArrayList coor : currentLst) {
        baseSum += (int)coor.get(1) - (int)currentLst.get(0).get(1);
      }
      valueMap.get(currentLst.get(0))[1] = baseSum;
      
      for (int i = 1; i < currentLst.size(); i++) {
        baseSum += (currentLst.get(i).get(1) - currentLst.get(i - 1).get(1)) * (2 * i - currentLst.size());
        valueMap.get(currentLst.get(i))[1] = baseSum;
      }
    }
    for (int yCoor : sameY.keySet()) {
      ArrayList<ArrayList<Integer>> currentLst = sameY.get(yCoor);
      int baseSum = 0;
      for (ArrayList coor : currentLst) {
        baseSum += (int)coor.get(0) - (int)currentLst.get(0).get(0);
      }
      valueMap.get(currentLst.get(0))[0] = baseSum;
      
      for (int i = 1; i < currentLst.size(); i++) {
        baseSum += (currentLst.get(i).get(0) - currentLst.get(i - 1).get(0)) * (2 * i - currentLst.size());
        valueMap.get(currentLst.get(i))[0] = baseSum;
      }
    }

    int mod = (int)(Math.pow(10, 9) + 7);
    long total = 0;
    for (ArrayList key : valueMap.keySet()) {
      total += (long)valueMap.get(key)[0] * (long)valueMap.get(key)[1] % mod;
    }
    total %= mod;
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
    pw.println(total);
    pw.close();
    
  }
}