package mlClustering;

import weka.core.Instance;

public class CTreePair {
	private ClusterTree<Instance> first;
	private ClusterTree<Instance> second;
	
	public CTreePair(ClusterTree<Instance> e1, ClusterTree<Instance> e2) {
		setFirst(e1);
		setSecond(e2);
	}

	public ClusterTree<Instance> getFirst() {
		return first;
	}

	public void setFirst(ClusterTree<Instance> first) {
		this.first = first;
	}

	public ClusterTree<Instance> getSecond() {
		return second;
	}

	public void setSecond(ClusterTree<Instance> second) {
		this.second = second;
	}
}
