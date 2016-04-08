package apache_storm.old._0_9_6.components;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class FirstBolt extends BaseRichBolt {

    public static String FIELD_NAME = "FIRST_BOLT_FIELD";
    private static final long serialVersionUID = 201604081420L;
    private OutputCollector collector;

    public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(FIELD_NAME));
    }

    public void execute(Tuple input) {
        Integer value = input.getInteger(0);
        System.out.println(getName() + " got: " + value);
        collector.emit(input, new Values(value * 2));
        collector.ack(input);
    }

    public static String getName() {
        return "FirstBolt";
    }
}