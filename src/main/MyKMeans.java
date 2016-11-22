package main;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.Clusterer;
import weka.core.DistanceFunction;
import weka.core.Instances;

/**
 * Created by Aidin Ahmad on 21/11/2016.
 */
public class MyKMeans extends AbstractClusterer implements Clusterer{
private int numClusters;


public MyKMeans() { /*code*/}

public void buildClusterer(Instances instances) throws Exception {

}

public int numberOfClusters() throws java.lang.Exception{
    return numClusters;
}

}
