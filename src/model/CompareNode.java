package model;

class ComparableNode <K extends Comparable<K>, E> {
	private K key;
	private E element;

	public ComparableNode(K key, E element) {
		this.key = key;
		this.element = element;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

}