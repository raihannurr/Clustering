package mlClustering;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.Clusterer;
import weka.core.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Aidin Ahmad on 21/11/2016.
 */
public class MyKMeans extends AbstractClusterer implements Clusterer {
	private int numClusters;
	private KMeansCluster[] clusters;
	private Instances dataset;
	private Instances centroids;
	private ArrayList<Attribute> attributes;
	int[] listSeed;
	
	public MyKMeans() { /*code*/}

	public MyKMeans(int numClusters) {
		this.numClusters = numClusters;
		clusters = new KMeansCluster[numClusters];
	}

	public void buildClusterer(Instances instances) throws Exception {
		this.dataset = instances;

		for(int i = 0; i<instances.numAttributes();i++) {
			attributes.add(dataset.attribute(i));
		}

		initSeed();
	}

    public int numberOfClusters() throws java.lang.Exception{
        return numClusters;
    }

    public KMeansCluster[] getAllClusters(){
	    return clusters;
    }

    public Instances getCentroids() {
        return centroids;
    }

    public void runClustering(){
        initSeed();
        initClusters();
        boolean isChanged = true;
        while(isChanged) {
            KMeansCluster[] prevClusters = new KMeansCluster[numClusters];
            for(int i = 0; i<numClusters; i++) {
                KMeansCluster prevCluster = new KMeansCluster(centroids.instance(i));
                prevClusters[i] = prevCluster;
            }
            reCluster();
            updateCentroid();
            isChanged = this.isChanged(prevClusters);
        }
    }

	private void initSeed(){
		listSeed = new int[numClusters];
		for (int i=0;i<numClusters;i++) {
			listSeed[i]=i;
		}

		centroids = new Instances("centroids",attributes,numClusters);
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<dataset.size(); i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);
		for (int i=0; i<numClusters; i++) {
			int idx =(list.get(i));
			centroids.add(dataset.instance(idx));
		}
	}

	private void initClusters(){
		for (int i = 0; i<numClusters; i++){
			clusters[i] = new KMeansCluster(centroids.instance(i),"cluster"+i,attributes,dataset.size());
		}
	}

	private void reCluster() {
		EuclideanDistance euclideanDistance = new EuclideanDistance();
		try {
			for (Instance instance: dataset) {
				int seedIdx = euclideanDistance.closestPoint(instance, centroids, listSeed);
				clusters[seedIdx].addMembers(instance);
			}
		} catch (Exception e) {

		}
	}

	private boolean isChanged(KMeansCluster[] prevClusters){
		boolean isChanged = false;
		int i = 0;
		while(!isChanged && i<numClusters) {
			if(!clusters[i].equals(prevClusters[i])){
				isChanged = true;
			}
			i++;
		}
		return isChanged;
	}

    private void updateCentroid(){
        for (int i=0; i<numClusters; i++) {
            clusters[i].moveCentroid();
            centroids.remove(i);
            centroids.add(i,clusters[i].getCentroid());
        }
    }


}
