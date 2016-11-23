package mlClustering;

import java.util.ArrayList;

import utils.ClusterDist;
import weka.clusterers.Clusterer;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

public class AgnesCluster implements Clusterer {
	
	private int numCluster;
	private ClusterTree<Instance> clusterTree;
	private int strategy;
	private int idManager;
	
	public AgnesCluster() {
		numCluster = 0;
		setIdManager(0);
		strategy = ClusterDist.MIN_DISTANCE;
	}
	
	public AgnesCluster(int strategy) {
		numCluster = 0;
		setIdManager(0);
		if (strategy == ClusterDist.MAX_DISTANCE) {
			this.strategy = strategy;
		} else {
			this.strategy = ClusterDist.MIN_DISTANCE;
		}
	}
	
	@Override
	public void buildClusterer(Instances arg0) throws Exception {
		//add all instances to leaf cluster
		ArrayList<ClusterTree<Instance>> treeTab = new ArrayList<ClusterTree<Instance>>();
		for(int i = 0; i < arg0.size(); i++) {
			ClusterTree<Instance> cluster = newClusterTree();
			treeTab.add(cluster);
		}
		
		
		numCluster = clusterTree.numNodes();
	}

	@Override
	public int clusterInstance(Instance arg0) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] distributionForInstance(Instance arg0) throws Exception {
		
		return null;
	}

	@Override
	public Capabilities getCapabilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfClusters() throws Exception {
		// TODO Auto-generated method stub
		return numCluster;
	}
	
	
	public ClusterTree<Instance> newClusterTree() {
		return new ClusterTree<Instance>(requestId());
	}
	public int requestId() {
		int id = idManager;
		idManager++;
		return id;
	}
	
	public ClusterTree<Instance> getClusterTree() {
		return clusterTree;
	}
	
	public int getIdManager() {
		return idManager;
	}

	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}

	public double distance(ArrayList<Instance> e1, ArrayList<Instance> e2) {
		if(strategy == ClusterDist.MIN_DISTANCE) {
			return ClusterDist.minDistance(e1, e2);
		} else {
			return ClusterDist.maxDistance(e1, e2);
		}
	}

	public void setClusterTree(ClusterTree<Instance> clusterTree) {
		this.clusterTree = clusterTree;
	}
}
