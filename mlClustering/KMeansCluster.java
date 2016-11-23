package mlClustering;

import weka.core.*;

import java.util.*;

/**
 * Created by Aidin Ahmad on 22/11/2016.
 */
public class KMeansCluster implements WeightedInstancesHandler{
    private Instance centroid;
    private Instances members;

    public KMeansCluster(){    }

    public KMeansCluster(Instances members, DenseInstance centroid) {
        this.members = members;
        this.centroid = new DenseInstance(centroid);
    }

    public KMeansCluster(Instance centroid) {
        this.centroid = centroid;
    }

    public KMeansCluster(Instance centroid, String name, ArrayList<Attribute> attributes, int capacity) {
        this.centroid = centroid;
        members = new Instances(name,attributes,capacity);
    }

    public void setMembers(Instances members){
        this.members= members;
    }

    public void setCentroid(DenseInstance centroid){
        this.centroid = centroid;
    }

    public void addMembers(Instance instance) {
        this.members.add(instance);
    }

    public Instances getMembers(){
        return members;
    }

    public Instance getCentroid() {
        return centroid;
    }

    public void moveCentroid(){
        double[] vals = new double[members.numAttributes()];
        Hashtable<Integer,double[]> nominalDists = new Hashtable<>();
        Hashtable<Integer, Double> weightMissing = new Hashtable<>();
        Hashtable<Integer,Double> weightNonMissing = new Hashtable<>();

        // Quickly calculate some relevant statistics
        for (int j = 0; j < members.numAttributes(); j++) {
            if (members.attribute(j).isNominal()) {
                nominalDists.put(j,new double[members.attribute(j).numValues()]);
            }
        }
        for (Instance inst : members) {
            for (int j = 0; j < members.numAttributes(); j++) {
                if (inst.isMissing(j)) {
                    double newWeight = weightMissing.getOrDefault(j,(double)0) + inst.weight();
                    weightMissing.put(j,newWeight);
                } else {
                    double newWeight = weightNonMissing.getOrDefault(j,(double)0) + inst.weight();
                    weightNonMissing.put(j,newWeight);
                    if (members.attribute(j).isNumeric()) {
                        vals[j] += inst.weight() * inst.value(j); // Will be overwritten in Manhattan case
                    } else {
                        nominalDists.get(j)[(int)inst.value(j)] += inst.weight();
                    }
                }
            }
        }

        for (int j = 0; j < members.numAttributes(); j++) {
            if (members.attribute(j).isNumeric()) {
                if  (weightNonMissing.get(j) > 0) {
                    vals[j] /= weightNonMissing.get(j);
                } else {
                    vals[j] = Utils.missingValue();
                }
            } else {
                double max = -Double.MAX_VALUE;
                double maxIndex = -1;
                for (int i = 0; i < nominalDists.get(j).length; i++) {
                    if (nominalDists.get(j)[i] > max) {
                        max = nominalDists.get(j)[i];
                        maxIndex = i;
                    }
                    if (max < weightMissing.get(j)) {
                        vals[j] = Utils.missingValue();
                    } else {
                        vals[j] = maxIndex;
                    }
                }
            }
        }
        centroid = new DenseInstance(1.0, vals);
    }

    public boolean isEqual(KMeansCluster cluster) {
        boolean equal = true;
        int i = 0;
        while ( equal && i < centroid.numAttributes()) {
            if (centroid.value(i) != cluster.centroid.value(i)) {
                equal = false;
            }
            i++;
        }
        return equal;
    }

    public void copyCentroid(KMeansCluster cluster){
        for(int i =0; i<centroid.numAttributes();i++){
            this.centroid.setValue(i,cluster.centroid.value(i));
        }
    }

}
