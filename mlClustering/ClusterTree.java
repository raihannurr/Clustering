package mlClustering;

import java.util.HashMap;

public class ClusterTree<T> {
	
	int root;
	int lastId;
	private HashMap<Integer, ClusterNode<T>> nodes;
	
	public ClusterTree() {
		root = 0;
		lastId = 0;
	}
	
	public ClusterTree(HashMap<Integer, ClusterNode<T>> nodes) {
		this.nodes = nodes;
	}
	
	public HashMap<Integer, ClusterNode<T>> getNodes() {
		return nodes;
	}
	
	public void setNodes(HashMap<Integer, ClusterNode<T>> nodes) {
		this.nodes = nodes;
	}
	
}
