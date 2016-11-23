package utils;

import java.util.ArrayList;
import java.util.Random;

import weka.core.Instance;

public class ClusterDist {
	public static final int SINGLE_LINK = 0;
	public static final int COMPLETE_LINK = 1;
	
	//jarak minimum antara cluster e1 dengan cluster e2
	public static double minDistance(ArrayList<Instance> e1, ArrayList<Instance> e2) {
		double min = Double.MAX_VALUE;
		double dist;
		for (Instance ins1 : e1) {
			for(Instance ins2 : e1) {
				dist = distanceInstance(ins1, ins2);
				if(dist < min) {
					min = dist;
				}
			}
		}
		return min;
	}
	
	//jarak terjauh antara cluster e1 dengan cluster e2
	public static double maxDistance(ArrayList<Instance> e1, ArrayList<Instance> e2) {
		double max = Double.MIN_VALUE;
		double dist;
		for (Instance ins1 : e1) {
			for(Instance ins2 : e1) {
				dist = distanceInstance(ins1, ins2);
				if(dist > max) {
					max = dist;
				}
			}
		}
		return max;
	}
	
	public static double distanceInstance(Instance ins1, Instance ins2) {
		Random rand = new Random();
		return Math.abs(rand.nextDouble()%100);
	}
}
