package freecoding.exercise.datastructure.graph;

import java.util.LinkedList;
import java.util.List;

public class GraphTestByMyself {
    static class Node{
        private int data = 0;
        private boolean marked = false;
        private List<Node> adjacents = new LinkedList<>();

        public Node(int data){
            this.data = data;
        }
    }

    void link(Node n1, Node n2) {
        if(!n1.adjacents.contains(n2)){
            n1.adjacents.add(n2);
        }
        if(!n2.adjacents.contains(n1)){
            n2.adjacents.add(n1);
        }
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[10];
        for (int i = 0; i <nodes.length; i++) {
            nodes[i] = new Node(i);
        }

        
    }

}
