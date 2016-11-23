package mlClustering;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
    	ClusterTree<Integer> c1 = new ClusterTree<Integer>(1);
    	ClusterTree<Integer> c2 = new ClusterTree<Integer>(1);
    	ClusterTree<Integer> c3 = new ClusterTree<Integer>(1);
    	for(int i = 0; i < 5; i++) {
    		c2.addElement(i);
    		c3.addElement(2*i + 1);
    	}
    	c1.addChildrenNode(c2);
    	c1.addChildrenNode(c3);
    	printArr(c1.getAllMembers());
    	System.out.println(c1.numNodes());
    }
    
    public static void printArr(ArrayList<?> array) {
    	for(Object obj : array) {
    		System.out.print(obj.toString() + " - ");
    	}
    	System.out.println("\n");
    }
}
