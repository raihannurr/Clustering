package mlClustering;

import weka.core.*;

import java.util.*;

/**
 * Created by Aidin Ahmad on 22/11/2016.
 */

/**
 * Kelas cluster untuk K-Means
 */
public class KMeansCluster implements WeightedInstancesHandler{
    private Instance centroid;
    private Instances members;

    /**
     * Conctructor
     */
    public KMeansCluster(){    }

    /**
     * Contructor
     * @param members
     * @param centroid
     */
    public KMeansCluster(Instances members, Instance centroid) {
        this.members = members;
        this.centroid = centroid;
    }

    /**
     * Constructor
     * @param centroid
     */
    public KMeansCluster(Instance centroid) {
        this.centroid = centroid;
    }

    /**
     * Contructor
     * @param centroid
     * @param name
     * @param attributes
     * @param capacity
     */
    public KMeansCluster(Instance centroid, String name, ArrayList<Attribute> attributes, int capacity) {
        this.centroid = centroid;
        members = new Instances(name,attributes,capacity);
    }

    /**
     * menjadikan Instances menjadi sebagai member kluster
     * @param members
     */
    public void setMembers(Instances members){
        this.members= members;
    }

    /**
     * menjadikan instance sebagai centroid
     * @param centroid
     */
    public void setCentroid(Instance centroid){
        this.centroid = centroid;
    }

    /**
     * menambahkan member ke kluster
     * @param instance
     */
    public void addMembers(Instance instance) {
        this.members.add(instance);
    }

    /**
     * mendapatkan member dari kluster
     * @return Instances
     */
    public Instances getMembers(){
        return members;
    }

    /**
     * mendapatkan cetroid dari cluster
     * @return Instance
     */
    public Instance getCentroid() {
        return centroid;
    }

    /**
     * mengganti centroid dengan centroid baru.
     * centorid baru dipilih dengan mencari rata-rata jarak yang dari centroid ke semua anggota
     * instance yang diberi bobot ditangani
     * missing attribute value juga ditangani
     */
    public void moveCentroid(){
        double[] vals = new double[members.numAttributes()];
        Hashtable<Integer,double[]> nominalDists = new Hashtable<>();
        Hashtable<Integer, Double> weightMissing = new Hashtable<>();
        Hashtable<Integer,Double> weightNonMissing = new Hashtable<>();

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

    /**
     * mengecek apakah 2 cluster sama atau tidak
     * diasumsikan 2 kluster yang memiliki centroid yang sama, juga memiliki anggota yang sama
     * @param cluster
     * @return boolean
     */
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

}
