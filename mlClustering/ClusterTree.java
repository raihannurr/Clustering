package mlClustering;

import java.util.ArrayList;

public class ClusterTree <T> {
	
	private int id;
	private ArrayList<Integer> children;
	private ArrayList<T> elements;
	private boolean isLeaf;
	private ArrayList<ClusterTree<T>> childrenNode;
	private ArrayList<Integer> descendants;
	public ClusterTree(int id) {
		this.id = id;
		children = new ArrayList<Integer>();
		setDescendants(new ArrayList<Integer>());
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
		elements = new ArrayList<T>();
	}
	
	public ClusterTree(int id, ArrayList<Integer> children) {
		this.id = id;
		this.children = children;
		setDescendants(new ArrayList<Integer>());
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
		elements = new ArrayList<T>();
		getDescendants().addAll(children);
	}
	
	public ClusterTree(int id, ArrayList<Integer> children, ArrayList<T> elements) {
		this.id = id;
		this.children = children;
		this.elements = elements;
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
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
		getDescendants().add(id);
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
	
	public boolean hasChildren() {
		return childrenNode.size() > 0;
	}

	public ArrayList<ClusterTree<T>> getChildrenNode() {
		return childrenNode;
	}

	public void setChildrenNode(ArrayList<ClusterTree<T>> childrenNode) {
		this.childrenNode = childrenNode;
		ArrayList<Integer> ch = new ArrayList<Integer>();
		children.clear();
		descendants.clear();
		for(ClusterTree<T> cluster : childrenNode) {
			ch.add(cluster.getId());
			addChildren(cluster.getId());
			getDescendants().add(cluster.getId());
			getDescendants().addAll(cluster.getDescendants());
		}
		this.children = ch;
	}
	
	public void addChildrenNode(ClusterTree<T> node) {
		childrenNode.add(node);
		addChildren(node.getId());
		descendants.add(node.getId());
		descendants.addAll(node.getDescendants());
		
	}
	
	public ClusterTree<T> getChildrenNodeAt(int idx) {
		return childrenNode.get(idx);
	}
	
	public boolean hasElement() {
		return elements.size() > 0;
	}
	
	public ArrayList<T> getAllMembers() {
		ArrayList<T> members = new ArrayList<T>();
		if (hasElement()) {
			members.addAll(elements);
		}
		
		for(ClusterTree<T> cluster : childrenNode) {
			members.addAll(cluster.getAllMembers());
		}
		return members;
	}
	
	public int numNodes() {
		int num = 1;
		for(ClusterTree<T> cluster : childrenNode) {
			num += cluster.numNodes();
		}
		return num;
	}

	public ArrayList<Integer> getDescendants() {
		return descendants;
	}

	public void setDescendants(ArrayList<Integer> descendants) {
		this.descendants = descendants;
	}
}
