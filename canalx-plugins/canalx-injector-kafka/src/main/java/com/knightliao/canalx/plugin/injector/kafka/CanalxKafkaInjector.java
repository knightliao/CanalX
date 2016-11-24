package com.knightliao.canalx.plugin.injector.kafka;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.knightliao.canalx.core.exception.CanalxInjectorException;
import com.knightliao.canalx.core.plugin.injector.ICanalInjector;
import com.knightliao.canalx.core.plugin.injector.IEntryProcessorAware;
import com.knightliao.canalx.core.plugin.injector.template.InjectorEntryProcessTemplate;

import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
public class CanalxKafkaInjector implements ICanalInjector, IEntryProcessorAware {

    private ConsumerConnector consumer;
    private String topic;
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
        String userDir = System.getProperty("user.dir");

        Properties kafkaProps = new Properties();
        kafkaProps.load(new FileInputStream(userDir + "/conf/kafka.properties"));
        ConsumerConfig consumerConfig = new ConsumerConfig(kafkaProps);

        String kafkaTopic = kafkaProps.getProperty("topic");

        this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
        this.topic = kafkaTopic;
    }

    @Override
    public void run() throws CanalxInjectorException {

    }

    @Override
    public void shutdown() throws CanalxInjectorException {

    }

    @Override
    public void setupProcessEntry(InjectorEntryProcessTemplate injectorEntryProcessTemplate) {
        this.injectorEntryProcessTemplate = injectorEntryProcessTemplate;
    }
}
