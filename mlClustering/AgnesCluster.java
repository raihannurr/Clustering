package mlClustering;

import java.util.Random;

import weka.clusterers.Clusterer;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

public class AgnesCluster implements Clusterer {
	
	private int numCluster;
	
	@Override
	public void buildClusterer(Instances arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int clusterInstance(Instance arg0) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] distributionForInstance(Instance arg0) throws Exception {
		// TODO Auto-generated method stub
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
	
	public double getInstance(Instance ins1, Instance ins2) {
		Random rand = new Random();
		return Math.abs(rand.nextDouble()%100);
	}
	
}
