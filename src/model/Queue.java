package model;

public class Queue<T,K> {
    private Node<T,K> tail;
    private Node<T,K> head;

    public Queue() {
        tail = null;
        head = null;
    }

    public void enqueue(T element,K key) {
        Node<T,K> node = new Node<>(element,key);
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
            Node<T,K> node = head;
            head = head.getNext();
            return node.getElement();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}