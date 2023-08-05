import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        /*
        Scanner s = new Scanner(new File("closing.in"));
        int N = s.nextInt();
        int M = s.nextInt();
        */
        BufferedReader br = new BufferedReader(new FileReader(new File("closing.in")));
        String line = br.readLine();
        String[] splitLine = line.split(" ");
        int N = Integer.parseInt(splitLine[0]);
        int M = Integer.parseInt(splitLine[1]);

        HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
        for (int i = 0; i < N; i++) {
            connections.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            String line1 = br.readLine();
            String[] splitLine1 = line1.split(" ");
            int one = Integer.parseInt(splitLine1[0]);
            int two = Integer.parseInt(splitLine1[1]);
            connections.get(one - 1).add(two - 1);
            connections.get(two - 1).add(one - 1);
        }

        UF unionFind = new UF(N);

        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        boolean[] ans = new boolean[N];
        HashSet<Integer> visited = new HashSet<>();
        for (int i = N - 1; i > -1; i--) {
            int newNum = array[i] - 1;
            unionFind.numComponents += 1;
            for (int neighbor : connections.get(newNum)) {
                if (visited.contains(neighbor)) {
                    unionFind.merge(newNum, neighbor);
                }
            }
            visited.add(newNum);
            ans[i] = unionFind.check();
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        for (int i = 0; i < N; i++) {
            if (ans[i]) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }
        pw.close();

    }
}

class UF {
    int[] parents;  
    int numComponents;

    public UF(int N) {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
        numComponents = 0;
    }

    public void merge(int a, int b) {
        if (!isConnected(a, b)) {
            numComponents -= 1;
        }
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

    public boolean check() {
        if (numComponents > 1) {
            return false;
        }
        return true;
    }
}