import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("art2.in"));
    int N = s.nextInt();

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    int[][] pairs = new int[N + 1][2];
    for (int i = 1; i < N + 1; i++) {
      pairs[i][0] = N + 1;
      pairs[i][1] = -1;
    }

    for (int i = 0; i < N; i++) {
      //if (array[i] != 0) {
        pairs[array[i]][0] = Math.min(pairs[array[i]][0], i);
        pairs[array[i]][1] = Math.max(pairs[array[i]][1], i);
      //}
    }

    int[] newArray = new int[N];
    for (int i = 1; i < N + 1; i++) {
      if (pairs[i][0] != N + 1) {
        newArray[pairs[i][0]] = i;
        newArray[pairs[i][1]] = i;
      }
    }

    ArrayList<Integer> finalArray = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if (newArray[i] != 0) {
        finalArray.add(newArray[i]);
      }
    }

    //System.out.println(finalArray);

    int numLayers = 0;
    boolean[] seen = new boolean[N + 1];

    int maxLayers = 0;

    for (int i = 0; i < finalArray.size(); i++) {
      if (!seen[finalArray.get(i)]) {
        seen[finalArray.get(i)] = true;
        numLayers += 1;
        maxLayers = Math.max(maxLayers, numLayers);
      } else {
        numLayers -= 1;
      }
      if (pairs[finalArray.get(i)][0] == pairs[finalArray.get(i)][1]) {
        numLayers -= 1;
      }
    }

    /*
    ArrayList<Integer> checkArray = new ArrayList<>();
    checkArray.add(array[0]);
    for (int i = 1; i < N; i++) {
        if (array[i] != array[i - 1]) {
            checkArray.add(array[i]);
        }
    }
    */
    
    boolean[] firstArray = new boolean[N];
    boolean[] lastArray = new boolean[N];
    seen = new boolean[N + 1];
    for (int i = 0; i < N; i++) {
        if (!seen[array[i]] && array[i] != 0) {
            firstArray[i] = true;
            seen[array[i]] = true;
        }
    }
    seen = new boolean[N + 1];
    for (int i = N - 1; i > -1; i--) {
        if (!seen[array[i]] && array[i] != 0) {
            lastArray[i] = true;
            seen[array[i]] = true;
        }
    }
    //System.out.println(Arrays.toString(firstArray));
    //System.out.println(Arrays.toString(lastArray));
    
    //ArrayList<Integer> stack = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    stack.push(0);
    for (int i = 0; i < N; i++) {
        if (firstArray[i] == true) {
            if (lastArray[i] == false) {
                stack.push(array[i]); 
            }
        } else {
            if (stack.peek() != array[i]) {
                maxLayers = -1;
                break;
            }
            if (lastArray[i] == true) {
                stack.pop();
            }
        }
        //System.out.println(stack);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
    pw.println(maxLayers);
    pw.close();
    
  }
}