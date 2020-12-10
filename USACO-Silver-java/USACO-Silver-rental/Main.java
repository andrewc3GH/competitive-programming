import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("rental.in"));
    int numCows = s.nextInt();
    int milkOrders = s.nextInt();
    int cowOrders = s.nextInt();
    int[] cowArray = new int[numCows];
    for (int i = 0; i < numCows; i++) {
      cowArray[i] = s.nextInt();
    }
    int[][] milkArray = new int[milkOrders][2];
    for (int i = 0; i < milkOrders; i++) {
      milkArray[i][0] = s.nextInt();
      milkArray[i][1] = s.nextInt();
    }
    int[] cowOrdersArray = new int[cowOrders];
    for (int i = 0; i < cowOrders; i++) {
      cowOrdersArray[i] = s.nextInt();
    }
    Arrays.sort(cowArray);
    Arrays.sort(milkArray, (int[] a, int[] b) -> {
      return b[1] - a[1];
    });
    Arrays.sort(cowOrdersArray);

    //System.out.println(Arrays.toString(cowArray));

    /*for (int[] item : milkArray) {
      System.out.println(Arrays.toString(item));
    }*/

    int orderIndex = 0;
    long[] milkAtIndex = new long[numCows];
    long currentProfit = 0;
    for (int i = numCows - 1; i > -1; i--) {
        long cowGallons = cowArray[i];
        for (int j = orderIndex; j < milkOrders; j++) {
            currentProfit += Math.min(cowGallons, milkArray[j][0]) * milkArray[j][1];
            if (cowGallons >= milkArray[j][0]) {
                cowGallons -= milkArray[j][0];
                orderIndex = j + 1;
            } else {
                milkArray[j][0] -= cowGallons;
                break;
            }
        }
        milkAtIndex[numCows - i - 1] = currentProfit;
    }
    //System.out.println(Arrays.toString(milkAtIndex));

    long numGallons = 0;
    for (int i = 0; i < numCows; i++) {
      numGallons += (long)cowArray[i];
    }
    long oldGallons = numGallons;
    long rentalProfit = 0;
    long maxProfit = milkAtIndex[numCows-1];
    //System.out.println(maxProfit);
    for (int numRentals = 0; numRentals < cowOrders; numRentals++) {
      if (numCows - 2 - numRentals < 0) {
        break;
      }
      numGallons = oldGallons;
      numGallons -= cowArray[numRentals];
      oldGallons = numGallons;
      rentalProfit += cowOrdersArray[cowOrders - 1 - numRentals];
      long milkProfit = milkAtIndex[numCows - 2 - numRentals];
      if (milkProfit + rentalProfit > maxProfit) {
        maxProfit = milkProfit + rentalProfit;
      }
      //System.out.println(milkProfit + " " + rentalProfit);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
    pw.println(maxProfit);
    pw.close();

    /*
    go from 0 to the number of cow rental orders, that is equal to the number of cow rental orders that will be fulfilled for this iteration. (every combination)
    greedy - send all the lowest cows to the most optimal rental order
    use the remaining cows to sell milk (just go through the sorted orders)

    hw is also 2017 december
    */ 
  }
}