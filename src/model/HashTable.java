package model;
import model.*;

public class HashTable<T,K> {
    private final int size = 11;
    private HNode[] table;
    public HashTable(){
        table = new HNode[size];
    }
    private int hash(String k){
        byte[] bytes = k.getBytes();
        int n = bytes[0];
        return n % size;
    }
    public void insert(T element, K key){
        int hash = hash(key.toString());
        if(table[hash] != null){
            HNode<T,K> node = new HNode(element,key);
            table[hash].add(node);
            table[hash] = node;
        }else{
            table[hash] = new HNode(element, key);
        }
    }

}