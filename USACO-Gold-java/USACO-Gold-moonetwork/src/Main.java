import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        //Scanner s = new Scanner(new File("moonetwork.in"));
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[][] array = new int[N][2];
        for (int i = 0; i < N; i++) {
            array[i] = new int[]{s.nextInt(), s.nextInt()};
        }

        Arrays.sort(array, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });
        /*
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
        */
        ArrayList<long[]> roads = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    roads.add(new long[]{i, j, (long)(array[i][0] - array[j][0]) * (long)(array[i][0] - array[j][0]) + (long)(array[i][1] - array[j][1]) * (long)(array[i][1] - array[j][1])});
                }
                
            }
        }
        Collections.sort(roads, (long[] a, long[] b) -> {
            return (int)(a[2] - b[2]);
        });

        UF unionFind = new UF(N);
        long cost = 0;
        for (int i = 0; i < roads.size(); i++) {
            if (unionFind.isConnected((int)roads.get(i)[0], (int)roads.get(i)[1])) {
                //dont do anything
            } else {
                //System.out.println("merge");
                unionFind.merge((int)roads.get(i)[0], (int)roads.get(i)[1]);
                cost += roads.get(i)[2];

            }
        }
        System.out.println(cost);

        
    }
}

class UF {
    int[] parents;  
  
    public UF(int N) {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }
  
    public void merge(int a, int b) {
        parents[getRoot(a)] = getRoot(b);
    }
  
    public int getRoot(int a) {
        if (parents[a] == a) {
            return a;
        } else {
            parents[a] = getRoot(parents[a]);
            return getRoot(parents[a]);
        }
    }
  
    public boolean isConnected(int a, int b) {
        return (getRoot(a) == getRoot(b));
    }
  }