package model;

import java.nio.charset.StandardCharsets;

public class HashTable<T,K> {
	private final int size = 17;
	private HNode[] table;
	public HashTable(){
		table = new HNode[size];
	}
	private int hash(String k){
		int value = k.hashCode();
		return value % size;
	}
	public void add(T element, K key){
		int hash = hash(key.toString());
		if(table[hash] != null){
			HNode<T,K> node = new HNode(element,key);
			table[hash].add(node);
			table[hash] = node;
		}else{
			table[hash] = new HNode(element, key);
		}
	}
	private int getNumericValue(byte[] bytes){
		int result = 0;
		for(byte b:bytes){
			result+=b;
		}
		return result;
	}
	public T search(K key){
		int hash = hash(key.toString());
		return get(key,hash);
	}
	private T get(K key, int hash){
		if(table[hash]!=null){
			HNode<T,K> node = table[hash];
			while(node!=null){
				if(node.getKey().equals(key)){
					return node.getElement();
				}else{
					node = node.getNext();
				}
			}
			return null;
		}else{
			return null;
		}
	}
	public String printTable(){
		String msj = "";
		for(int i=0;i<size;i++){
			if(table[i]!=null){
				msj+="\nrow "+i+" : "+table[i].list();
			}
		}
		return msj;
	}

}