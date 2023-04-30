package model;

import java.nio.charset.StandardCharsets;

public class HashTable<T,K> {
	private final int size = 11;
	private HNode[] table;
	public HashTable(){
		table = new HNode[size];
	}
	private int hash(String k){
		byte[] bytes = k.getBytes(StandardCharsets.US_ASCII);
		int n = calculateASCII(bytes);
		return n % size;
	}
	private int calculateASCII(byte[] bytes){
		int result = 0;
		for(byte b:bytes){
			result+=b;
		}
		return result;
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
	public T search(K key){
		int hash = hash(key.toString());
		return search(key,hash);
	}
	private T search(K key, int hash){
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
				msj+="\n"+table[i].list();
			}
		}
		return msj;
	}

}