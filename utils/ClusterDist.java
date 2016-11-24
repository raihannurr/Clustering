package utils;

import java.util.ArrayList;
import weka.core.EuclideanDistance;
import weka.core.Instance;

public class ClusterDist {
	public static final int SINGLE_LINK = 0;
	public static final int COMPLETE_LINK = 1;
	
	//jarak minimum antara cluster e1 dengan cluster e2
	public static double minDistance(ArrayList<Instance> e1, ArrayList<Instance> e2, EuclideanDistance edist) {
		double min = Double.MAX_VALUE;
		double dist;
		for (int i = 0; i < e1.size(); i++) {
			for(int j = 0; j < e2.size(); j++) {
				dist = distanceInstance(e1.get(i), e2.get(j), edist);
				if(dist < min) {
					min = dist;
				}
			}
		}
		return min;
	}
	
	//jarak terjauh antara cluster e1 dengan cluster e2
	public static double maxDistance(ArrayList<Instance> e1, ArrayList<Instance> e2, EuclideanDistance edist) {
		double max = Double.MIN_VALUE;
		double dist;
		for (int i = 0; i < e1.size(); i++) {
			for(int j = 0; j < e2.size(); j++) {
				dist = distanceInstance(e1.get(i), e2.get(j),edist);
				if(dist > max) {
					max = dist;
				}
			}
		}
		return max;
	}
	
	public static double distanceInstance(Instance ins1, Instance ins2, EuclideanDistance edist) {
		return edist.distance(ins1, ins2);
	}
}
