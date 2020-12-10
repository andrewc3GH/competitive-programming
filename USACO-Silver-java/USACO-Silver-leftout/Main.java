import java.io.*;
import java.util.*;

//HW: finish this problem (watch out for 2x2 testcase, change arraylist contains to array equals method below, if returnNegative is false: return differentRow + 1 + " " + differentColumn + 1 else: return -1), finish cowjump problem (use min/max of x/y coordinates of each line segment to sort and evaluate with sliding window)

//Read output directions more carefully!!!

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("leftout.in"));
    int N = s.nextInt();
    s.nextLine();
    int[][] cowArray = new int[N][N];
    for (int i = 0; i < N; i++) {
      String currentLine = s.nextLine();
      for (int j = 0; j < N; j++) {
        if (currentLine.substring(j, j + 1).equals("R")) {
          cowArray[i][j] = 1;
        } else {
          cowArray[i][j] = 0;
        }
      }
    }

    for (int[] row : cowArray) {
      System.out.println(Arrays.toString(row));
    }

    for (int i = 0; i < N; i++) {
      if (cowArray[i][0] != 0) {
        for (int j = 0; j < N; j++) {
          if (cowArray[i][j] == 1) {
            cowArray[i][j] = 0;
          } else if (cowArray[i][j] == 0) {
            cowArray[i][j] = 1;
          }
        }
      }
    }
    for (int i = 0; i < N; i++) {
      if (cowArray[0][i] != 0) {
        for (int j = 0; j < N; j++) {
          if (cowArray[j][i] == 1) {
            cowArray[j][i] = 0;
          } else if (cowArray[j][i] == 0) {
            cowArray[j][i] = 1;
          }
        }
      }
    }
    for (int[] row : cowArray) {
      System.out.println(Arrays.toString(row));
    }
    int count = 0;
    int differentRow = 0;
    HashMap<String, ArrayList<Integer>> list = new HashMap<>();
    for (int i = 0; i < N; i++) {
      if (list.containsKey(Arrays.toString(cowArray[i]))) {
        list.get(Arrays.toString(cowArray[i])).add(i);
      } else {
        list.put(Arrays.toString(cowArray[i]), new ArrayList<Integer>());
        list.get(Arrays.toString(cowArray[i])).add(i);
      }
      /*
      if (!cowArray[i].equals(cowArray[0])) {
        System.out.println(i);
        count += 1;
        differentRow = i;
        boolean works = true;
        for (int[] element : list) {
          System.out.println("1: " + Arrays.toString(element));
          System.out.println("2: " + Arrays.toString(cowArray[0]));
          if (element.equals(cowArray[0])) {
            works = false;
            break;
          }
        }
        if (works == true) { //use for loop and Arrays.equals()
          list.add(cowArray[i]);
        }
      }*/
    }
    System.out.println(list);
    for (String key : list.keySet()) {
      if (list.get(key).size() == 1) {
        differentRow = list.get(key).get(0);
      }
    }
    System.out.println(differentRow);
    boolean returnNegative = false;
    if (list.size() > 2 || (list.size() == 1 && N == 2) || (list.size() == 1)) {
      returnNegative = true;
    }
    /*
    if (count > 1) {
      differentRow = 0;
    }*/

    int counter = 0;
    int differentColumn = 0;
    for (int i = 0; i < N; i++) {
      if (cowArray[differentRow][i] != 0) {
        counter += 1;
        differentColumn = i;
      }
    }
    if (counter > 1) {
      differentColumn = 0;
    }
    System.out.println(differentRow);
    System.out.println(differentColumn);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")));
    if (returnNegative) {
      pw.println(-1);
    } else {
      pw.println((differentRow + 1) + " " + (differentColumn + 1));
    }
    pw.close();
  }
}