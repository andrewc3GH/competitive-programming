import java.io.*;
import java.util.*;

class newNewMain {  
    public static void main(String[] args) throws IOException {
        //Scanner s = new Scanner(new File("braceletcrossings.in"));
        Scanner s = new Scanner(System.in);
        int T = s.nextInt(); //num cases
        
        for (int i = 0; i < T; i++) {
            int N = s.nextInt(); //num bracelets
            int M = s.nextInt(); //num lines
            ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                lines.add(new ArrayList<>());
                int lineLen = s.nextInt();
                //lines.get(j).add(0);
                for (int k = 0; k < lineLen; k++) {
                    lines.get(j).add(s.nextInt());
                }
                //lines.get(j).add(0);
            }
            //System.out.println(i + ": " + lines);
            
            //make over arching node -> nodeLst[0]

            Node[] nodeLst = new Node[N + 1];
            for (int j = 0; j < N + 1; j++) {
                nodeLst[j] = new Node(j);
            }
            
            //check for skips (exists in one and then disappears and then shows up again)
            //check for crisscross (so 1 2 1 2 doesnt work)
            //check for ordering (so 1221 -> 2112 doesnt work) 

            HashSet<Integer> totalVisited = new HashSet<>();
            HashSet<Integer> missing = new HashSet<>();

            boolean works = true;
            for (int j = 0; j < M; j++) {
                Stack<Integer> stack = new Stack<Integer>();
                stack.push(0);
                boolean[] seen = new boolean[N + 1];
                HashSet<Integer> visited = new HashSet<>();
                for (int k = 0; k < lines.get(j).size(); k++) {
                    int a = lines.get(j).get(k);
                    if (seen[a]) {
                        if (stack.peek() != a) { //not consecutive
                            works = false;
                            //System.out.println("not consecutive");
                            break;
                        }
                        stack.pop();
                    } else {
                        seen[a] = true;
                        if (nodeLst[a].parent == null) {
                            nodeLst[a].parent = nodeLst[stack.peek()];
                        } else if (nodeLst[a].parent.color != stack.peek()) { //parent is wrong
                            works = false;
                            //System.out.println("parent is wrong");
                            break;
                        }
                        stack.push(a);
                    }
                    for (int b : visited) {
                        if (a != b && nodeLst[a].getOrderVal(b) == 0) {
                            nodeLst[a].order[b] = 1;
                            nodeLst[b].order[a] = -1;
                        } else if (a != b && !visited.contains(a) && nodeLst[a].getOrderVal(b) == -1) {
                            works = false;
                            //System.out.println("wrong order");
                            break;
                        }
                    }
                    visited.add(a);
                }
                for (int num : visited) {
                    if (missing.contains(num)) {
                        works = false;
                        //System.out.println("missing");
                        break;
                    }
                }
                for (int num : visited) {
                    totalVisited.add(num);
                }
                for (int num : totalVisited) {
                    if (!visited.contains(num)) {
                        missing.add(num);
                    }
                }
            }
            if (works) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            //System.out.println(works);
        }
    }
}

class Node {
    public int color;
    public Node parent;
    public int[] order;
    
    public Node (int a) {
        color = a;
        order = new int[51];
        //0 = no info, -1 = this is before, 1 = this is after
    }

    public void setParent (Node a) {
        parent = a;
    }

    public int getOrderVal(int a) {
        return order[a];
    }

}