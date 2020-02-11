package freecoding.exercise.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkedLists<T> {
    private Node<T> node;
    public class Node<T> {
        private final T value;
        private Node<T> node;

        public Node(T value){
            this.value = value;
        }
    }

    @Test
    public void test1(){
        LinkedLists<Integer> integerLinkedLists = new LinkedLists<>();
        integerLinkedLists.addNode(new Node(1));

        assertEquals(integerLinkedLists.getSize(),1);
    }

    public void addNode(Node<T> node) {
        if(this.node == null){
            this.node = node;
            return;
        }
        Node n;
        for(n = this.node ;n != null;n = n.node);
        n.node = node;
    }

    public int getSize(){
        int i = 0;
        for(Node n = this.node;n != null;n = n.node, i++);
        return i;
    }

}
