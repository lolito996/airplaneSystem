package model;

public class Queue<T,K> {
    private HNode<T,K> tail;
    private HNode<T,K> head;

    public Queue() {
        tail = null;
        head = null;
    }

    public void enqueue(T element,K key) {
        HNode<T,K> node = new HNode<>(element,key);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        } else {
            HNode<T,K> node = head;
            head = head.getNext();
            return node.getElement();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}