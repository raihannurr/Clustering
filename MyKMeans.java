package com.company;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.Clusterer;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instances;

/**
 * Created by Aidin Ahmad on 21/11/2016.
 */
public class MyKMeans extends AbstractClusterer implements Clusterer{
private int numClusters;
private int iteration;
private Instances startPoints;
private Instances centroids;
private DistanceFunction distanceFunction = new EuclideanDistance();
private Instances currentInstances;
private Instances previewsInstances;

public MyKMeans() { /*code*/
    numClusters = 10;
    iteration = 0;
}

public MyKMeans(int numClusters) throws Exception{ /*code*/
    this.numClusters = numClusters;
    iteration = 0;
}

public void setNumClusters(int numClusters) {
    this.numClusters = numClusters;
}

public int getNumClusters() {
    return numClusters;
}

public void buildClusterer(Instances instances) throws Exception {
    this.currentInstances = instances;

}

public int numberOfClusters() throws java.lang.Exception{
    return numClusters;
}

}

