import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {

        //kind of like merge sort
        //sort rows and columns, automatically take first row and first column
        //choose between current row and current column, calculate how many fences to remove
        //increase that pointer
        
        //compute the rows/columns instead of each individual fence
        //keep track of how many boxes have been reached in each row/column
        //sort the rows/columns and go through them crossing fences
        //upadate how many boxes have been reached in each row/column

        //cross out one row and one column first

        Scanner s = new Scanner(new File("fencedin.in"));
        int A = s.nextInt();
        int B = s.nextInt();
        int n = s.nextInt(); //vertical fences
        int m = s.nextInt(); //horizontal fences
        
        int[] verticalFences = new int[n + 2];
        for (int i = 0; i < n; i++) {
            verticalFences[i] = s.nextInt();
        }
        verticalFences[n] = 0;
        verticalFences[n + 1] = A;
        Arrays.sort(verticalFences);
        int[] horizontalSpacing = new int[n + 1];
        
        for (int i = 1; i < n + 2; i++) {
            horizontalSpacing[i - 1] = verticalFences[i] - verticalFences[i - 1];
        }

        int[] horizontalFences = new int[m + 2];
        for (int i = 0; i < m; i++) {
            horizontalFences[i] = s.nextInt();
        }
        horizontalFences[m] = 0;
        horizontalFences[m + 1] = A;
        Arrays.sort(horizontalFences);
        int[] verticalSpacing = new int[m + 1];
        for (int i = 1; i < m + 2; i++) {
            verticalSpacing[i - 1] = horizontalFences[i] - horizontalFences[i - 1];
        }

        //length, horizontalspacing = 0 verticalspacing = 1, index
        int[][] array = new int[n + m + 2][3];
        for (int i = 0; i < n + 1; i++) {
            array[i] = new int[]{horizontalSpacing[i], 0, i};
        }
        for (int i = 0; i < m + 1; i++) {
            array[i + n + 1] = new int[]{verticalSpacing[i], 1, i};
        }
        Arrays.sort(array, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        /*
        for (int[] a : array) {
            System.out.println(Arrays.toString(a));
        }
        */

        int[] columnVisited = new int[n + 1];
        int[] rowVisited = new int[m + 1];

        UF unionFind = new UF((n + 1), (m + 1));

        int total = 0;
        for (int i = 0; i < n + m + 2; i++) {
            //horizontalspacing
            if (array[i][1] == 0) {
                int currentTotal = (m * array[i][0]);
                /*if (columnVisited[array[i][2]] > 0) {
                    currentTotal -= (columnVisited[array[i][2]] - 1) * array[i][0];
                }*/
                for (int j = 0; j < m; j++) {
                    if (unionFind.isConnected(new int[]{array[i][2], j}, new int[]{array[i][2], j + 1})) {
                        currentTotal -= array[i][0];
                    } else {
                        unionFind.merge(new int[]{array[i][2], j}, new int[]{array[i][2], j + 1});
                    }
                }
                total += currentTotal;
                /*
                for (int j = 0; j < m + 1; j++) {
                    rowVisited[j] += 1;
                }
                */
                columnVisited[array[i][2]] = m + 1;
            } else {
                int currentTotal = (n * array[i][0]);
                /*if (rowVisited[array[i][2]] > 0) {
                    currentTotal -= (rowVisited[array[i][2]] - 1) * array[i][0];
                }*/
                for (int j = 0; j < n; j++) {
                    if (unionFind.isConnected(new int[]{j, array[i][2]}, new int[]{j + 1, array[i][2]})) {
                        currentTotal -= array[i][0];
                    } else {
                        unionFind.merge(new int[]{j, array[i][2]}, new int[]{j + 1, array[i][2]});
                    }
                }
                total += currentTotal;
                /*
                for (int j = 0; j < n + 1; j++) {
                    columnVisited[j] += 1;
                }
                */
                rowVisited[array[i][2]] = n + 1;
            }
            if (unionFind.numComponents == 1) {
                break;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("fencedin.out"))));
        pw.println(total);
        pw.close();

        

    }
}

class UF {
    int[][][] parents;  
    int numComponents;

    public UF(int N, int M) {
        parents = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                parents[i][j] = new int[]{i, j};
            }
        }
        numComponents = N * M;
    }

    public void merge(int[] a, int b[]) {
        if (!isConnected(a, b)) {
            numComponents -= 1;
        }
        parents[getRoot(a)[0]][getRoot(a)[1]] = getRoot(b);
    }

    public int[] getRoot(int[] coor) {
        if (parents[coor[0]][coor[1]] == coor) {
            return coor;
        } else {
            parents[coor[0]][coor[1]] = getRoot(parents[coor[0]][coor[1]]);
            return getRoot(parents[coor[0]][coor[1]]);
        }
    }

    public boolean isConnected(int[] a, int[] b) {
        return (getRoot(a) == getRoot(b));
    }

    public boolean check() {
        if (numComponents > 1) {
            return false;
        }
        return true;
    }
}