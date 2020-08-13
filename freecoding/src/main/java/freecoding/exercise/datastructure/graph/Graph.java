package freecoding.exercise.datastructure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Queue<T> {
    private List<T> lists = new LinkedList<>();
    public void enqueue(T node){
        lists.add(node);
    }
    public T dequeue(){
        return lists.remove(0);
    }

    public boolean isEmpty() {
        return lists.isEmpty();
    }
}

public class Graph {
    class Node {
        private int data;
        private List<Node> adjacent = new LinkedList<>();
        private boolean marked = false;

        public Node(int data){
            this.data = data;
        }
    }
    Node[] nodes;
    public Graph(int size){
        nodes = new Node[size];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
    }
    public void addEdge(int i1, int i2) {
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if(!n1.adjacent.contains(n2)){
            n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)){
            n2.adjacent.add(n1);
        }
    }
    public void clear(){
        for (Node node : nodes) {
            node.marked = false;
        }
        System.out.println();
    }
    public void dfs() {
        dfs(0);
    }

    public void dfs(int index){
        Node root = nodes[index];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.marked = true;
        while(!stack.isEmpty()){
            Node r = stack.pop();
            for (Node n : r.adjacent) {
                if(!n.marked){
                    stack.push(n);
                    n.marked = true;
                }
            }
            visit(r);
        }
    }

    public void bfs() {
        bfs(0);
    }

    private void bfs(int index) {
        Node root = nodes[index];
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        root.marked = true;
        while(!queue.isEmpty()){
            Node r = queue.dequeue();
            for (Node n : r.adjacent) {
                if(!n.marked){
                    queue.enqueue(n);
                    n.marked = true;
                }
            }
            visit(r);
        }
    }

    private void dfsR(Node r){
        if(r == null) return;
        r.marked = true;
        visit(r);
        for (Node n : r.adjacent) {
            if(!n.marked) {
                dfsR(n);
            }
        }
    }

    private void dfsR(int index){
        dfsR(nodes[index]);
    }

    private void dfsR(){
        dfsR(0);
    }

    private void visit(Node n) {
        System.out.print(n.data + " ");
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);

        System.out.print("g.dfs();");
        g.dfs();
        g.clear();
        System.out.print("g.bfs();");
        g.bfs();
        g.clear();
        System.out.print("g.dfsR();");
        g.dfsR();
        g.clear();
        System.out.print("g.dfs(3);");
        g.dfs(3);
        g.clear();
        System.out.print("g.bfs(3);");
        g.bfs(3);
        g.clear();
        System.out.print("g.dfsR(3);");
        g.dfsR(3);
        g.clear();

    }
}

