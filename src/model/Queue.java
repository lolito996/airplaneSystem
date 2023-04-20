package  model;
public class Queue<T>{
    private T e;
    private Node <T> tail;
    private Node <T> head;

    public Queue(){
        tail=null;
        head=null;
    }


    public void enqueue(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }


    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Queue is Empty");
        }else{
            Node node = head;
            head = head.getNext();
            return (T)node.getElement();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}