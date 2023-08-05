import java.io.*;
import java.util.*;

class Main {
  static int N;
  static boolean[][] visited;
  static String[] stringArray;
  static ArrayList<int[]> indexLst;
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("mooyomooyo.in"));
    N = s.nextInt();
    int K = s.nextInt();
    stringArray = new String[N];
    for (int i = 0; i < N; i++) {
      stringArray[i] = s.next();
    }
    String[] oldArray = new String[N];
    String[] oldOldArray = new String[N];
    String[] oldOldOldArray = new String[N];
    String[] oldOldOldOldArray = new String[N];
    String[] oldOldOldOldOldArray = new String[N];
    String[] oldOldOldOldOldOldArray = new String[N];
    char zero = '0';
    while (oldOldOldOldOldOldArray.equals(stringArray) == false) {
      oldOldOldOldOldOldArray = oldOldOldOldOldArray;
      oldOldOldOldOldArray = oldOldOldOldArray;
      oldOldOldOldArray = oldOldOldArray;
      oldOldOldArray = oldOldArray;
      oldOldArray = oldArray;
      oldArray = stringArray;
      visited = new boolean[N][10];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < 10; j++) {
          indexLst = new ArrayList<>();
          char currentNum = stringArray[i].charAt(j);
          if (currentNum != '0' && search(currentNum, i, j) >= K) {
            for (int k = 0; k < indexLst.size(); k++) {
              //stringArray[(int)indexLst.get(k)[0]][(int)indexLst.get(k)[1]] = "0";
              //(int)indexLst.get(k)[1]
              stringArray[(int)indexLst.get(k)[0]] = stringArray[(int)indexLst.get(k)[0]].substring(0, (int)indexLst.get(k)[1]) + zero + stringArray[(int)indexLst.get(k)[0]].substring((int)indexLst.get(k)[1] + 1); 
            }
          } 
        }
      }
      for (int i = N - 1; i > -1; i--) {
        for (int j = 0; j < 10; j++) {
          int iCounter = i;
          while (iCounter < N - 1) {
            if (stringArray[iCounter].charAt(j) != '0' && stringArray[iCounter + 1].charAt(j) == '0') {
              char tempChar = stringArray[iCounter].charAt(j);
              stringArray[iCounter] = stringArray[iCounter].substring(0, j) + zero + stringArray[iCounter].substring(j + 1); 
              stringArray[iCounter + 1] = stringArray[iCounter + 1].substring(0, j) + tempChar + stringArray[iCounter + 1].substring(j + 1); 
              iCounter += 1;
            } else {
              break;
            }
          }
        }
      }
      /*for (int i = 0; i < N; i++) {
        System.out.println(stringArray[i]);
      }*/
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
    for (int i = 0; i < N; i++) {
      pw.println(stringArray[i]);
    }
    pw.close();
  }

  public static int search(char currentNum, int i, int j) {
    if (i >= N || i < 0 || j >= 10 || j < 0 || stringArray[i].charAt(j) != currentNum || visited[i][j]) {
      return 0;
    }
    //increase size
    visited[i][j] = true;
    int[] tempArray = new int[2];
    tempArray[0] = i;
    tempArray[1] = j;
    indexLst.add(tempArray);
    return 1 + search(currentNum, i - 1, j) + search(currentNum, i + 1, j) + search(currentNum, i, j - 1) + search(currentNum, i, j + 1);
  }
}