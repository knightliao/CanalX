package com.knightliao.canalx.plugin.injector.kafka;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.exception.CanalxInjectorException;
import com.knightliao.canalx.core.exception.CanalxInjectorInitException;
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
    private ConsumerConnector consumer = null;

    // topics
    private String[] topics;

    // process entry template
    private InjectorEntryProcessTemplate injectorEntryProcessTemplate;

    // thread executors
    private ExecutorService executor = null;

    //
    private final static String CONFIG_FILE_NAME = "kafka.properties";

    @Override
    public void init() throws CanalxInjectorInitException {

        try {
            this.loadConfigAndInit();
        } catch (Exception e) {
            throw new CanalxInjectorInitException(e);
        }
    }

    private void loadConfigAndInit() throws IOException {

        Properties kafkaProps = new Properties();
        URL url = CanalxKafkaInjector.class.getClassLoader().getResource(CONFIG_FILE_NAME);
        if (url != null) {
            LOGGER.info("loading config file {}", url.toString());

            kafkaProps.load(new InputStreamReader(new FileInputStream(url.getPath()),
                    "utf-8"));

            ConsumerConfig consumerConfig = new ConsumerConfig(kafkaProps);

            //
            String kafkaTopicStr = kafkaProps.getProperty("topics");
            this.topics = kafkaTopicStr.split(",");

            // consumer
            this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
        } else {

            LOGGER.warn("cannot find config file {}", CONFIG_FILE_NAME);
        }
    }

    @Override
    public void run() throws CanalxInjectorException {

        if (consumer != null) {

            // set topic map
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

            for (String topic : topics) {
                topicCountMap.put(topic, 1);

            }

            // get stream
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);

            // executors
            executor = Executors.newFixedThreadPool(consumerMap.size());
            for (String topicStr : consumerMap.keySet()) {

                List<KafkaStream<byte[], byte[]>> newOrderStreams = consumerMap.get(topicStr);

                ConsumerIterator<byte[], byte[]> it = newOrderStreams.get(0).iterator();

                executor.submit(new InjectorSupport(it, topicStr, injectorEntryProcessTemplate));
            }
        }
    }

    @Override
    public void shutdown() throws CanalxInjectorException {

        if (executor != null) {
            executor.shutdown();

            try {
                if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                    LOGGER.info("Timed out waiting for consumer threads (topic: {} ) to shut down, exiting uncleanly",
                            topics);
                }
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted during shutdown, exiting uncleanly");
            }
        }
    }

    @Override
    public void setupProcessEntry(InjectorEntryProcessTemplate injectorEntryProcessTemplate) {
        this.injectorEntryProcessTemplate = injectorEntryProcessTemplate;
    }
}
