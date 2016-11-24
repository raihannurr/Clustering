package mlClustering;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * Created by Aidin Ahmad on 24/11/2016.
 */
public class Main {
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("asdasdasd");
        try {
            System.out.println("tes");
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("iris.arff");
            AgnesCluster agnes = new AgnesCluster();
            MyKMeans kmeans = new MyKMeans(3);
            Instances datas = source.getDataSet();
            //System.out.println(datas.a);
            kmeans.buildClusterer(datas);
            //agnes.buildClusterer(datas);
            //System.out.println(agnes.toString());
            System.out.println("aku");
        } catch (Exception e) {

        }
    }
}
