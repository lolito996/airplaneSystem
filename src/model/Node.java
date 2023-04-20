package model;
public class Node<T> {

    private T e;
    private Node previous;
    private Node next;

    public Node(T e) {
        this.e = e;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public T getElement() {
        return e;
    }

    public void setElement(T e) {
        this.e = e;
    }


}