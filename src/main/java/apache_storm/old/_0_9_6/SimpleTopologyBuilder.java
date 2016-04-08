package apache_storm.old._0_9_6;

import apache_storm.old._0_9_6.components.DummySpout;
import apache_storm.old._0_9_6.components.FirstBolt;
import apache_storm.old._0_9_6.components.SecondBolt;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class SimpleTopologyBuilder {

    public static StormTopology createTopology() {
        TopologyBuilder builder = new TopologyBuilder();        
        builder.setSpout(DummySpout.getName(), new DummySpout(), 1);        

        builder.setBolt(FirstBolt.getName(), new FirstBolt(), 1)
                .shuffleGrouping(DummySpout.getName());
        
        builder.setBolt(SecondBolt.getName(), new SecondBolt(), 1)
                .shuffleGrouping(FirstBolt.getName());
        return builder.createTopology();
    }
}
