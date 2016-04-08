package apache_storm.old._0_9_6;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.utils.Utils;

public class LocalRunner {

    public static void main(String[] args) {
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(2);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("test", conf, SimpleTopologyBuilder.createTopology());
        Utils.sleep(90000);
        cluster.killTopology("test");
        cluster.shutdown();
    }
}
