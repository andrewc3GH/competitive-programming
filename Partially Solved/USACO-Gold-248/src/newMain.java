import java.io.*;
import java.util.*;

class newMain {  
  public static int actualMax;
  public static void main(String[] args) throws IOException {
    
    Scanner s = new Scanner(new File("248.in"));
    int N = s.nextInt();

    actualMax = 0;

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    ArrayList<int[]> firstArray = new ArrayList<>();
    int currentNum = array[0];
    int currentQuantity = 0;
    for (int i = 0; i < N; i++) {
      if (currentNum == array[i]) {
        currentQuantity += 1;
      } else {
        firstArray.add(new int[]{currentNum, currentQuantity});
        currentNum = array[i];
        currentQuantity = 1;
      }
    }
    firstArray.add(new int[]{currentNum, currentQuantity});

    int lowestNumber = 1;
    /*
    for (int i = 0; i < firstArray.size(); i++) {
      if (firstArray.get(i)[0] == lowestNumber && firstArray.get(i)[1] % 2 == 0) {
        firstArray.get(i)[0] = 2;
        firstArray.get(i)[1] /= 2;
      }
    }*/
    recursion(firstArray, lowestNumber);
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
    pw.println(actualMax);
    pw.close();
  }

  public static int recursion(ArrayList<int[]> array, int lowestNumber) {

    for (int i = 0; i < array.size(); i++) {
      if (array.get(i)[0] == lowestNumber && array.get(i)[1] % 2 == 0) {
        array.get(i)[0] = lowestNumber + 1;
        array.get(i)[1] /= 2;
      }
    }

    //write code to combine ((1, 2), (2, 1)) -> ((2, 1), (2, 1)) but we want ((2, 2))
    //expand the array list into its individual components, then combine them back into the arraylist
    for (int i = 0; i < array.size() - 1; i++) {
      if (array.get(i)[0] == array.get(i + 1)[0]) {
        array.get(i + 1)[1] += array.get(i)[1];
        array.get(i)[0] = 0;
        array.get(i)[1] = 0;
      }
    }
    /*
    System.out.println(array.size());
    for (int i = 0; i < array.size(); i++) {
      System.out.print(Arrays.toString(array.get(i)) + " ");
    }
    System.out.println();
    */

    ArrayList<int[]> activeGroup = new ArrayList<>();
    for (int i = 0; i < array.size(); i++) {
      if (array.get(i)[0] == lowestNumber) {
        int[] modifiedArr = {array.get(i)[0] + 1, array.get(i)[1]/2};
        if (modifiedArr[1] != 0) {
          activeGroup.add(modifiedArr);
        }
        /*System.out.print("Active: ");
        for (int j = 0; j < activeGroup.size(); j++) {
          System.out.print(Arrays.toString(activeGroup.get(j)) + " ");
        }
        System.out.println();*/
        recursion(activeGroup, lowestNumber + 1);
        activeGroup = new ArrayList<>();
        activeGroup.add(modifiedArr);
      } else {
        if (array.get(i)[1] != 0) {
          activeGroup.add(array.get(i));
        }
        if (i == array.size() - 1) {
          recursion(activeGroup, lowestNumber + 1);
        }
      }
    }
    
    int maxNum = 0;
    for (int i = 0; i < array.size(); i++) {
      maxNum = Math.max(maxNum, array.get(i)[0]);
    }
    actualMax = Math.max(actualMax, maxNum);
    return maxNum;
  }
}
