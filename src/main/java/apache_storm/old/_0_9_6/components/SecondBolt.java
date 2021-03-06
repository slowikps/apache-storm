package apache_storm.old._0_9_6.components;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class SecondBolt extends BaseRichBolt {

    private static final long serialVersionUID = 201604081420L;
    private OutputCollector collector;

    public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
    
    public void execute(Tuple input) {
        Integer value = input.getIntegerByField(FirstBolt.FIELD_NAME);
        System.out.println(getName() + " got: " + value);
        collector.ack(input);
    }

    public static String getName() {
        return "SecondBolt";
    }
}