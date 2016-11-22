package main;

import java.util.ArrayList;

public class Node <T> {
	private ArrayList<T> element;
	private boolean isLeaf;
	
	public Node() {
		this.element = null;
	}
	
	public Node(ArrayList<T> element) {
		this.setElement(element);
	}

	public ArrayList<T> getElement() {
		return element;
	}

	public void setElement(ArrayList<T> element) {
		this.element = element;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
