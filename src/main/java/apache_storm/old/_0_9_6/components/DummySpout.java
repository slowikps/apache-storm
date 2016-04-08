package apache_storm.old._0_9_6.components;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class DummySpout extends BaseRichSpout {

    public static String FIELD_NAME = "DUMMY_SPOUT_FIELD";
    private static final long serialVersionUID = 201604081420L;

    private transient int number = 0;

    private SpoutOutputCollector collector;
    
    public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        System.out.println("Declare output fields");
        declarer.declare(new Fields(FIELD_NAME));
    }

    public void nextTuple() {
        if(number < 10) {
            //Second param is message id for ack purpose!
            collector.emit(new Values(number++), number);
        }
        Utils.sleep(100);
    }

    @Override
    public void ack(Object msgId) {
       System.out.println("ack: " + msgId);
    }
    
    @Override
    public void fail(Object msgId) {
        System.out.println("failed: " + msgId);
    }

    public static String getName() {
        return "DummySpout";
    }
}