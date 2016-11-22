package com.company;

import weka.core.*;

import java.util.ArrayList;

/**
 * Created by Aidin Ahmad on 22/11/2016.
 */
public class KMeansCluster {
    DenseInstance centroid;
    Instances members;

    public KMeansCluster(){    }

    public KMeansCluster(Instances instances, DenseInstance centroid) {
        this.members = new Instances(instances);
        this.centroid = new DenseInstance(centroid);
    }

    public void setMembers(Instances members){
        this.members = members;
    }

    public void setCentroid(DenseInstance centroid){
        this.centroid = centroid;
    }

    public void changeCentroid(){
        DenseInstance newCentroid = new DenseInstance(centroid.numAttributes());
        newCentroid = findCentroid();
        setCentroid(newCentroid);
    }

    public DenseInstance findCentroid() {
        DenseInstance newCentroid = new DenseInstance(centroid.numAttributes());
        for (int i=0; i<centroid.numAttributes();i++) {
            newCentroid.setValue(i, getNewCentroidAttrValueStr(i));
        }
        return newCentroid;
    }

    public double getNewCentroidAttrValueStr(int idx){
        double newValue = 0;
        double sum = 0;
        for (int i=0; i<members.numInstances(); i++) {
            EuclideanDistance euclideanDistance = new EuclideanDistance();
            sum += getDistance(centroid,members.instance(i),euclideanDistance);
        }
        newValue = sum/members.numInstances();
        return newValue;
    }

    private double getDistance(Instance instance1, Instance instance2, DistanceFunction function) {
        return function.distance(instance1,instance2);
    }
}
