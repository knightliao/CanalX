package com.knightliao.canalx.plugin.injector.kafka;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.exception.CanalxInjectorException;
import com.knightliao.canalx.core.plugin.injector.ICanalInjector;
import com.knightliao.canalx.core.plugin.injector.IInjectorEntryProcessorAware;
import com.knightliao.canalx.core.plugin.injector.template.InjectorEntryProcessTemplate;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.plugin.injector.kafka.support.InjectorSupport;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-injector-kafka")
public class CanalxKafkaInjector implements ICanalInjector, IInjectorEntryProcessorAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKafkaInjector.class);

    // kafka consumer
    private ConsumerConnector consumer;

    // topic
    private String topic;

    // process entry template
    private InjectorEntryProcessTemplate injectorEntryProcessTemplate;

    @Override
    public void init() throws CanalxInjectorException {

        try {
            this.loadConfigAndInit();
        } catch (Exception e) {
            throw new CanalxInjectorException(e);
        }
    }

    private void loadConfigAndInit() throws IOException {

        Properties kafkaProps = new Properties();
        URL url = CanalxKafkaInjector.class.getClassLoader().getResource("kafka.properties");
        kafkaProps.load(new InputStreamReader(new FileInputStream(url.getPath()),
                "utf-8"));

        ConsumerConfig consumerConfig = new ConsumerConfig(kafkaProps);

        String kafkaTopic = kafkaProps.getProperty("topic");

        this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
        this.topic = kafkaTopic;
    }

    @Override
    public void run() throws CanalxInjectorException {

        // get topic data
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);

        List<KafkaStream<byte[], byte[]>> newOrderStreams = consumerMap.get(topic);

        ConsumerIterator<byte[], byte[]> it = newOrderStreams.get(0).iterator();

        // inject
        InjectorSupport injectorSupport = new InjectorSupport(it, injectorEntryProcessTemplate);
        injectorSupport.processMsg();
    }

    @Override
    public void shutdown() throws CanalxInjectorException {

    }

    @Override
    public void setupProcessEntry(InjectorEntryProcessTemplate injectorEntryProcessTemplate) {
        this.injectorEntryProcessTemplate = injectorEntryProcessTemplate;
    }
}
