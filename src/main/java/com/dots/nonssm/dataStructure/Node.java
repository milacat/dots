package com.dots.nonssm.dataStructure;

public class Node {
    public Node next;
    public Object data;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(Object data) {
        this.data = data;
    }
}
