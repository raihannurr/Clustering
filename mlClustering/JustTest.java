package mlClustering;

import java.util.ArrayList;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class JustTest {

    public static void main(String[] args) throws Exception {
	// write your code here
    	  // TODO code application logic here
        DataSource source = new DataSource("C:/Users/muhtarh/Desktop/weather.nominal.arff");
        AgnesCluster agnes = new AgnesCluster();        
        //MyKMeans kmeans = new MyKMeans(3);
        Instances datas = source.getDataSet();    
        //System.out.println(datas.a);
        //kmeans.buildClusterer(datas);
        agnes.buildClusterer(datas);
        System.out.println(datas.size());
        System.out.println(agnes.getClusterTree().getAllMembers().size());
        System.out.println(agnes);
    }
    
    public static void printArr(ArrayList<?> array) {
    	for(Object obj : array) {
    		System.out.print(obj.toString() + " - ");
    	}
    	System.out.println("\n");
    }
}
