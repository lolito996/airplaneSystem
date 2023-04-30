package model;

public class HNode<T,K> {
    private T element;
    private K key;
    private HNode<T,K> next;
    public HNode<T,K> getNext() {
        return next;
    }
    public void setNext(HNode<T,K> next)
    {
        this.next = next;
    }
    public HNode(T element,K key) {
        this.element = element;
        this.key = key;
        this.next = null;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public T getElement() {
        return element;
    }
    public void setElement(T element) {
        this.element = element;
    }
    public void add(HNode<T,K> e){
        e.setNext(this);
    }
    public int size(){
        HNode<T,K> current = this;
        int cont = 1;
        while(current.getNext()!=null){
            cont++;
            current = current.getNext();
        }
        return cont;
    }
    public String list(){
        HNode<T,K> current = this;
        String cont = "";
        while(current!=null){
            cont += " : "+current.getKey();
            current = current.getNext();
        }
        return cont;
    }



}