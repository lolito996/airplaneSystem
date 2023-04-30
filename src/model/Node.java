package model;

public class Node<T> {
    private T e;
    private Node<T> next;
    private Node<T> previous;
    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next)
    {
        this.next = next;
    }
    public Node<T> getPrevious() {
        return next;
    }
    public void setPrevious(Node<T> next)
    {
        this.next = next;
    }
    public Node(T element) {
        this.e = element;
        this.next = null;
        this.previous = null;
    }
    public T getElement() {
        return e;
    }
    public void setElement(T element) {
        this.e = element;
    }
    public void add(Node<T> current,Node<T> e){
        while(current.getNext()!=null){
            add(current.getNext(),e);
        }
        current.setNext(e);
        e.setPrevious(current);
    }
    public int size(){
        Node<T> current = this;
        int cont = 1;
        while(current.getNext()!=null){
            cont++;
            current = current.getNext();
        }
        return cont;
    }
}
