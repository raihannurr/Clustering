package mlClustering;

import java.util.ArrayList;

public class ClusterNode <T> {
	
	private int id;
	private ArrayList<Integer> children;
	private ArrayList<T> elements;
	private boolean isLeaf;
	private ArrayList<ClusterNode<?>> childrenNode;
	
	public ClusterNode(int id) {
		this.id = id;
		children = new ArrayList<Integer>();
		isLeaf = false;
		childrenNode = new ArrayList<ClusterNode<?>>();
	}
	
	public ClusterNode(int id, ArrayList<Integer> children) {
		this.id = id;
		this.children = children;
		isLeaf = false;
		childrenNode = new ArrayList<ClusterNode<?>>();
	}
	
	public ClusterNode(int id, ArrayList<Integer> children, ArrayList<T> elements) {
		this.id = id;
		this.children = children;
		this.elements = elements;
		isLeaf = false;
		childrenNode = new ArrayList<ClusterNode<?>>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Integer> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Integer> children) {
		this.children = children;
	}
	
	public int getNumChildren() {
		return children.size();
	}
	
	public int getChildrenAt(int idx) {
		if(idx >= children.size()) {
			return -1;
		} else {
			return children.get(idx);
		}
	}
	
	public void addChildren(int id) {
		children.add(id);
	}
	
	public void addElement(T e) {
		elements.add(e);
	}
	
	public T getElementAt(int idx) {
		return elements.get(idx);
	}

	public ArrayList<T> getElements() {
		return elements;
	}

	public void setElements(ArrayList<T> elements) {
		this.elements = elements;
	}
	
	
	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public ArrayList<ClusterNode<?>> getChildrenNode() {
		return childrenNode;
	}

	public void setChildrenNode(ArrayList<ClusterNode<?>> childrenNode) {
		this.childrenNode = childrenNode;
	}
	
	public void addChildrenNode(ClusterNode<?> node) {
		childrenNode.add(node);
	}
	
	public ClusterNode<?> getChildrenNodeAt(int idx) {
		return childrenNode.get(idx);
	}
}
