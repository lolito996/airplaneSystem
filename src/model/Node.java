package model;
public class Node<T,H> {
    private T e;
    private H hash;
    private Node<T,H> next;

    public Node(T aid, H hash){
        e = aid;
        this.hash = hash;
        this.next = null;
    }
    public T getElement(){
        return e;
    }
    public Node<T,H> getNext() {
        return next;
    }
    public void setNext(Node<T,H> next) {
        this.next = next;
    }
    public void add(Node<T,H> e){
        e.setNext(this);
    }
    public int size(){
        Node<T,H> current = this;
       int cont = 1;
       while(current.getNext()!=null){
           cont++;
           current = current.getNext();
       }
       return cont;
    }

}
