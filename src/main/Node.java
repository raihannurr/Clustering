package main;

public class Node <T> {
	private T element;
	private boolean isLeaf;
	
	public Node() {
		this.element = null;
	}
	public Node(T element) {
		this.setElement(element);
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
