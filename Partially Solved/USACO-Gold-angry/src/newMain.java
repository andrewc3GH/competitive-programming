import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("angry.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    Arrays.sort(array);

    int[] leftArr = new int[N];
    leftArr[0] = 0;
    int index = 0;
    while (index < N - 1) {
      if (array[index + 1] - array[index] < leftArr[index] + 1) {
        int otherIndex = index + 1;
        while (otherIndex < N) {
          if (array[otherIndex] - array[index] <= leftArr[index] + 1) {
            leftArr[otherIndex] = leftArr[index] + 1;
          } else {
            index = otherIndex - 2;
            break;
          }
          otherIndex += 1;
        }
        if (otherIndex >= N) {
          index = otherIndex - 2;
        }
      } else {
        leftArr[index + 1] = Math.max(array[index + 1] - array[index], leftArr[index] + 1);
      }
      index += 1;
    }
    //System.out.println(Arrays.toString(leftArr));

    int[] arrayCopy = new int[N];
    for (int i = 0; i < N; i++) {
      arrayCopy[i] = array[N - i - 1] * -1;
    }

    int[] rightArr = new int[N];
    rightArr[0] = 0;
    index = 0;
    while (index < N - 1) {
      if (arrayCopy[index + 1] - arrayCopy[index] < rightArr[index] + 1) {
        int otherIndex = index + 1;
        while (otherIndex < N) {
          if (arrayCopy[otherIndex] - arrayCopy[index] <= rightArr[index] + 1) {
            rightArr[otherIndex] = rightArr[index] + 1;
          } else {
            index = otherIndex - 2;
            break;
          }
          otherIndex += 1;
        }
        if (otherIndex >= N) {
          index = otherIndex - 2;
        }
      } else {
        rightArr[index + 1] = Math.max(arrayCopy[index + 1] - arrayCopy[index], rightArr[index] + 1);
      }
      index += 1;
    }
    //System.out.println(Arrays.toString(rightArr));

    int[] newRight = new int[N];
    for (int i = 0; i < N; i++) {
      newRight[i] = rightArr[N - i - 1];
    }

    System.out.println(Arrays.toString(leftArr));
    System.out.println(Arrays.toString(newRight));

    double minVal = 999999999;
    for (int i = 0; i < N - 1; i++) {
      double newVal = 0;
      if (leftArr[i] == newRight[i + 1]) {
        newVal = leftArr[i] + 1;
      } else {
        newVal = Math.max(leftArr[i], newRight[i + 1]);
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
