package mlClustering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ClusterTree <T> {
	
	private int id;
	private ArrayList<Integer> children;
	private ArrayList<T> elements;
	private boolean isLeaf;
	private ArrayList<ClusterTree<T>> childrenNode;
	private Set<Integer> descendants;
	private int level;
	
	public ClusterTree(int id) {
		this.id = id;
		children = new ArrayList<Integer>();
		setDescendants(new HashSet<Integer>());
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
		elements = new ArrayList<T>();
		setLevel(0);
	}
	
	public ClusterTree(int id, int level) {
		this.id = id;
		children = new ArrayList<Integer>();
		setDescendants(new HashSet<Integer>());
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
		elements = new ArrayList<T>();
		this.setLevel(level);
	}
	
	public ClusterTree(int id, ArrayList<Integer> children, int level) {
		this.id = id;
		this.children = children;
		setDescendants(new HashSet<Integer>());
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
		elements = new ArrayList<T>();
		getDescendants().addAll(children);
		this.setLevel(level);
	}
	
	public ClusterTree(int id, ArrayList<Integer> children, ArrayList<T> elements, int level) {
		this.id = id;
		this.children = children;
		this.elements = elements;
		isLeaf = false;
		childrenNode = new ArrayList<ClusterTree<T>>();
		this.setLevel(level);
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
	
	@Override
	public String toString() {
		return toString(0);
	}
	
	public String toString(int numSpace) {
		//print space
		String res = "";
		for(int i = 0; i< numSpace; i++) {
			res += "  ";
		}
		res += "ID = " + Integer.toString(id) + " Level = " + Integer.toString(level) + " Children : " + ArrToString(children);
		if(hasElement()) {
			res += " Element : " + ArrToString(elements);
		}
		res += "\n";
		for(int i = 0; i < childrenNode.size(); i++) {
			res += childrenNode.get(i).toString(numSpace+1);
		}
		return res;
	}
	
	public String ArrToString(ArrayList<?> arr) {
		String res = "{";
		if(!arr.isEmpty()) {
			for(int i = 0; i < arr.size(); i++) {
				res += arr.get(i).toString() + ", ";
			}
			res = res.substring(0, res.length()-2);
			
		}
		res += "}";
		return res;
	}
	public int numNodes() {
		int num = 1;
		for(ClusterTree<T> cluster : childrenNode) {
			num += cluster.numNodes();
		}
		return num;
	}

	public Set<Integer> getDescendants() {
		return descendants;
	}

	public void setDescendants(HashSet<Integer> hashSet) {
		this.descendants = hashSet;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
