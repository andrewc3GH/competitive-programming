import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("angry.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    Arrays.sort(array);

    int[] leftArr = new int[N];
    int[] firstrightArr = new int[N];

    leftArr[0] = 0;
    leftArr[1] = array[1] - array[0];
    int currentVal = array[1] - array[0];
    int currentReach = array[1] + currentVal + 1;
    //consider adding while loop to look at one element and compute the following elements that work and then moving on
    /*for (int i = 2; i < N; i++) {
      if (array[i] <= currentReach) {
        leftArr[i] = currentVal + 1;
        System.out.println(i);
        //current reach and current val should increase here in certain cases
      } else {
        leftArr[i] = Math.max(leftArr[i - 1] + 1, array[i] - array[i - 1]);
        currentVal = Math.max(leftArr[i - 1] + 1, array[i] - array[i - 1]);
        currentReach = currentVal + array[i] + 1;
      }
    }*/
    int outsideIndex = 1;
    while (outsideIndex < leftArr.length - 1) {
      int index = outsideIndex;
      currentVal = leftArr[outsideIndex] + 1;
      currentReach = leftArr[outsideIndex] + array[outsideIndex] + 1;
      while (index < leftArr.length - 1) {
        index += 1;
        if (array[index] <= currentReach) {
          leftArr[index] = currentVal;
        } else {
          leftArr[index] = Math.max(currentVal + 1, array[index] - array[index - 1]);
          if (index != outsideIndex + 1) {
            index -= 1;
          }
          break;
        }
      }
      outsideIndex = index;
    }
    //System.out.println(Arrays.toString(leftArr));
    
    int[] arrayCopy = new int[N];
    for (int i = 0; i < N; i++) {
      arrayCopy[i] = -1 * array[N - i - 1];
    }
    firstrightArr[0] = 0;
    firstrightArr[1] = arrayCopy[1] - arrayCopy[0];
    currentVal = arrayCopy[1] - arrayCopy[0];
    currentReach = arrayCopy[1] + currentVal + 1;
    /*for (int i = N - 3; i > -1; i--) {
      if (array[i] >= currentReach) {
        rightArr[i] = currentVal + 1;
      } else {
        rightArr[i] = Math.max(rightArr[i + 1] + 1, array[i + 1] - array[i]);
        currentVal = Math.max(rightArr[i + 1] + 1, array[i + 1] - array[i]);
        currentReach = array[i] - currentVal - 1;
      }
    }
    System.out.println(Arrays.toString(rightArr));*/

    //flip and make everything negative
    
    outsideIndex = 1;
    while (outsideIndex < firstrightArr.length - 1) {
      int index = outsideIndex;
      currentVal = firstrightArr[outsideIndex] + 1;
      currentReach = firstrightArr[outsideIndex] + arrayCopy[outsideIndex] + 1;
      while (index < firstrightArr.length - 1) {
        index += 1;
        if (arrayCopy[index] <= currentReach) {
          firstrightArr[index] = currentVal;
        } else {
          firstrightArr[index] = Math.max(currentVal + 1, arrayCopy[index] - arrayCopy[index - 1]);
          if (index != outsideIndex + 1) {
            index -= 1;
          }
          break;
        }
      }
      outsideIndex = index;
    }
    int[] rightArr = new int[N];
    for (int i = 0; i < N; i++) {
      rightArr[i] = firstrightArr[N - i - 1];
    }

    double minVal = 999999999;
    for (int i = 0; i < N - 1; i++) {
      double newVal = 0;
      if (leftArr[i] == rightArr[i + 1]) {
        newVal = leftArr[i] + 1;
      } else {
        newVal = Math.max(leftArr[i], rightArr[i + 1]);
      }
      if ((double)(array[i + 1] - array[i])/2 > newVal) {
        newVal = (array[i + 1] - array[i]);
        newVal/=2;
      }
      minVal = Math.min(newVal, minVal);
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
    pw.println(minVal);
    pw.close();
  }
}
