import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    /*Scanner s = new Scanner(new File("trapped.in"));
    int N = s.nextInt();
    int B = s.nextInt();
    int[][] baleArray = new int[N][2];
    int halfIndex = -1;
    for (int i = 0; i < N; i++) {
      int[] temp = new int[2];
      temp[0] = s.nextInt();
      temp[1] = s.nextInt();
      baleArray[i] = temp;
    }*/
    BufferedReader f = new BufferedReader(new FileReader("trapped.in"));
    String line = f.readLine();
    String[] splitLine = line.split(" ");
    int N = Integer.parseInt(splitLine[0]);
    int B = Integer.parseInt(splitLine[1]);
    int[][] baleArray = new int[N][2];
    int halfIndex = N;
    for (int i = 0; i < N; i++) {
      int[] temp = new int[2];
      String newline = f.readLine();
      String[] newsplitLine = newline.split(" ");
      int a = Integer.parseInt(newsplitLine[0]);
      int b = Integer.parseInt(newsplitLine[1]);
      temp[0] = a;
      temp[1] = b;
      baleArray[i] = temp;
    }

    Arrays.sort(baleArray, (int[] a, int[] b) -> {
      return a[1] - b[1];
    });

    for (int i = 0; i < N; i++) {
      if (baleArray[i][1] > B) {
        halfIndex = i;
        break;
      }
    }

    ArrayList<int[]> leftArray = new ArrayList<>();
    for (int i = 0; i < halfIndex; i++) {
      leftArray.add(baleArray[i]);
    }

    Collections.sort(leftArray, (int[] a, int[] b) -> {
      return a[0] + a[1] - b[0] - b[1];
    });
    for (int[] element : leftArray) {
      System.out.println(Arrays.toString(element));
    }
    
    int maxIndex = N;
    int minCounter = 1111111111;
    for (int i = leftArray.size() - 1; i > -1; i--) {
      int currentReach = leftArray.get(i)[0] + leftArray.get(i)[1];
      for (int j = halfIndex; j < maxIndex; j++) {
        if (currentReach >= baleArray[j][1]) {
          int counter = baleArray[j][1] - baleArray[j][0] - leftArray.get(i)[1];
          if (counter < minCounter) {
            minCounter = counter;
          }
        } else {
          maxIndex = j + 1;
        }
      }
    }

    ArrayList<int[]> rightArray = new ArrayList<>();
    for (int i = N - 1; i > halfIndex - 1; i--) {
      rightArray.add(baleArray[i]);
    }

    Collections.sort(rightArray, (int[] a, int[] b) -> {
      return (a[1] - a[0]) - (b[1] - b[0]);
    });

    int newMaxIndex = 0;
    int newMinCounter = 1111111111;
    for (int i = 0; i < rightArray.size(); i++) {
      int currentReach = rightArray.get(i)[1] - rightArray.get(i)[0];
      for (int j = halfIndex - 1; j > newMaxIndex - 1; j--) {
        if (currentReach <= baleArray[j][1]) {
          int newCounter = rightArray.get(i)[1] - baleArray[j][1] - baleArray[j][0];
          
          if (newCounter < newMinCounter) {
            newMinCounter = newCounter;
          }
        } else {
          newMaxIndex = j;
        }
      }
    }
    System.out.println(newMinCounter);
    System.out.println(minCounter);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));
    if (minCounter < 0 && newMinCounter < 0) {
      pw.println(0);
    } else if (B < baleArray[0][1] || B > baleArray[baleArray.length - 1][1]) {
      pw.println(-1);
    } else if (minCounter > newMinCounter) {
      pw.println(newMinCounter);
    } else if (minCounter < newMinCounter) {
      pw.println(minCounter);
    } else if (minCounter == 1111111111 && newMinCounter == 1111111111) {
      pw.println(-1);
    } else {
      pw.println(minCounter);
    }
    pw.close();
  }
}