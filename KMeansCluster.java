package mlClustering;

import weka.core.*;

import java.util.ArrayList;

/**
 * Created by Aidin Ahmad on 22/11/2016.
 */
public class KMeansCluster {
    DenseInstance centroid;
    ArrayList<Integer> membersIdx;

    public KMeansCluster(){    }

    public KMeansCluster(ArrayList<Integer> membersIdx, DenseInstance centroid) {
        this.membersIdx = new ArrayList<>(membersIdx);
        this.centroid = new DenseInstance(centroid);
    }

    public void setMembers(ArrayList<Integer> membersIdxs){
        this.membersIdx = new ArrayList<>(membersIdxs);
    }

    public void setCentroid(DenseInstance centroid){
        this.centroid = centroid;
    }

    public void changeCentroid(Instances instances){
        DenseInstance newCentroid = new DenseInstance(centroid.numAttributes());
        newCentroid = findCentroid(instances);
        setCentroid(newCentroid);
    }

    public DenseInstance findCentroid(Instances instances) {
        DenseInstance newCentroid = new DenseInstance(centroid.numAttributes());
        for (int attrIdx=0; attrIdx<centroid.numAttributes();attrIdx++) {
            newCentroid.setValue(attrIdx, getNewCentroidAttrValue(instances, attrIdx));
        }
        return newCentroid;
    }

    public double getNewCentroidAttrValue(Instances instances, int attrIdx){
        double newValue = 0;
        double sum = 0;
        for (int idx : membersIdx) {
            EuclideanDistance euclideanDistance = new EuclideanDistance();
            sum += getDistance(centroid,instances.instance(idx),euclideanDistance);
        }
        newValue = sum/membersIdx.size();
        return newValue;
    }

    private double getDistance(Instance instance1, Instance instance2, DistanceFunction function) {
        return function.distance(instance1,instance2);
    }
}
