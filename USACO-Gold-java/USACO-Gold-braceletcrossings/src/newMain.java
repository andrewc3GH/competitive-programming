import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("braceletcrossings.in"));
    int T = s.nextInt(); //num cases
    
    for (int i = 0; i < T; i++) {
        int N = s.nextInt(); //num bracelets
        int M = s.nextInt(); //num lines
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        for (int j = 0; j < M; j++) {
            lines.add(new ArrayList<>());
            int lineLen = s.nextInt();
            lines.get(j).add(0);
            for (int k = 0; k < lineLen; k++) {
                lines.get(j).add(s.nextInt());
            }
            lines.get(j).add(0);
        }
        System.out.println(i + ": " + lines);
        
        //make over arching node -> nodeLst[0]

        Node[] nodeLst = new Node[N + 1];
        for (int j = 0; j < N + 1; j++) {
            nodeLst[j] = new Node(j);
        }
        /*
        HashSet<Integer> totalSeen = new HashSet<>();
        HashSet<Integer> badLst = new HashSet<>();
        ArrayList<Integer> seen = new ArrayList<>();
        for (int j = 0; j < M; j++) {
            for (int num : totalSeen) {
                if (!lines.get(j).contains(num)) {
                    badLst.add(num);
                }
            }
            for (int k = 0; k < lines.get(j).size(); k++) {
                int a = lines.get(j).get(k);
                totalSeen.add(a);

                if (badLst.contains(a)) {
                    System.out.println("very bad cuz we skipped u");
                    break;
                }
                if (seen.contains(a)) {
                    if (seen.get(seen.size() - 1) == a) {
                        seen.remove(seen.size() - 1);
                    } else {
                        System.out.println("bad");
                        break;
                    }
                } else {

                    if (nodeLst[a].hasParent) { //has a parent already (parent can be null)
                        if (seen.size() == 0 && !nodeLst[a].nullParent) {
                            System.out.println("bad 1");
                            break;
                        } else if (!nodeLst[a].nullParent && nodeLst[a].parent.color != nodeLst[seen.get(seen.size() - 1)].color) {
                            System.out.println("bad 2");
                            break;
                        } else if (nodeLst[a].nullParent && seen.size() > 0) {
                            System.out.println("bad 3");
                        }
                    } else { //does not have a parent
                        if (seen.size() == 0) {
                            nodeLst[a].setParent(null);
                        } else {
                            nodeLst[a].setParent(nodeLst[seen.get(seen.size() - 1)]);
                        }
                    }
                    seen.add(a);
                }
            }
        }*/

    }
    
    }
}

/*
class Node {
    public int color;
    public Node parent;
    public HashSet<Node> children;
    public boolean hasParent;
    public boolean nullParent;
    
    public Node (int a) {
        color = a;
        children = new HashSet<>();
    }

    public void setParent (Node a) {
        parent = a;
        if (a == null) {
            nullParent = true;
        }
        hasParent = true;
    }

    public void addChild (Node a) {
        children.add(a);
    }

}
*/