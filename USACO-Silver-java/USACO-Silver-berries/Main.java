import java.io.*;
import java.util.*;
//binary search to find berry value for each basket
//loop through trees and add the berry value to each basket
//if there are more baskets than trees with the minimum berry value, add berries from the trees with the most berries

//keep track of number of full baskets
//then mod each item in array by optimal number
//keep track of how many more baskets we need
//sort array and add this many baskets of berries
class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("berries.in"));
    int N = s.nextInt();
    int K = s.nextInt();
    ArrayList<Integer> berryArray = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      berryArray.add(s.nextInt());
    }
    Collections.sort(berryArray);
    Collections.reverse(berryArray);
    int bessieBerries = 0;
    for (int k = 1; k < berryArray.get(0) + 1; k++) {
      int currentNum = k;
      ArrayList<Integer> copyArray = new ArrayList<>();
      int fullBaskets = 0;
      for (int j = 0; j < N; j++) {
        fullBaskets += berryArray.get(j)/currentNum;
        copyArray.add(berryArray.get(j) % currentNum);
      }
      int otherBerries = 0;
      Collections.sort(copyArray);
      Collections.reverse(copyArray);
      //System.out.println(k + " " + copyArray);
      if (fullBaskets > K) {
        fullBaskets = K;
      }
      int bessieGetsThese = 0;
      for (int j = 0; j < K - fullBaskets; j++) {
        if (j >= copyArray.size()) {
          break;
        }
        otherBerries += copyArray.get(j);
        if (j + fullBaskets > K/2) {
          bessieGetsThese += copyArray.get(j);
        }
      }
      int bessieBaskets = 0;
      if (fullBaskets > K/2) {
        bessieBaskets = fullBaskets - K/2;
      }
      //System.out.println(k + " " + bessieBaskets + " " + bessieGetsThese);
      int currentBerries = bessieBaskets * k + bessieGetsThese;
      if (currentBerries > bessieBerries) {
        bessieBerries = currentBerries;
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
    pw.println(bessieBerries);
    pw.close();
  }
  public static int findIndex(int min, int max) {
    return (int)((min + max)/2);
  }
}