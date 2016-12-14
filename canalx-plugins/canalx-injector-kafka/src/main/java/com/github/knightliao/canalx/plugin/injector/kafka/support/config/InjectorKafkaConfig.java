package com.github.knightliao.canalx.plugin.injector.kafka.support.config;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.support.config.IConfig;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.google.common.base.Preconditions;

import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import lombok.Data;
import scala.actors.threadpool.Arrays;

/**
 *
 */
@Data
public class InjectorKafkaConfig implements IConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(InjectorKafkaConfig.class);

    private String[] topics;
    private List<String> topicList;

    // kafka consumer
    private ConsumerConnector consumer = null;

    //
    private final static String CONFIG_FILE_NAME = "kafka.properties";

    @Override
    public void init(ICanalxContext iCanalxContext) throws Exception {

        Preconditions.checkNotNull(iCanalxContext, "iCanalxContext can not be null");

        //
        String currentTopics = iCanalxContext.getProperty("canalx.plugin.injector.kafka.topics");
        this.topics = currentTopics.split(",");

        topicList = Arrays.asList(topics);

        LOGGER.debug("injector config: topics:{}", topicList);

        // load kafka config
        loadConfigAndInit();
    }

    @Override
    public void init() throws Exception {
    }

    private void loadConfigAndInit() throws Exception {

        Properties kafkaProps = new Properties();
        InputStream inputStream = InjectorKafkaConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);

        if (inputStream != null) {

            kafkaProps.load(inputStream);

            LOGGER.info("loading injector kafka config file {}", CONFIG_FILE_NAME);

            ConsumerConfig consumerConfig = new ConsumerConfig(kafkaProps);

            // consumer
            this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);

        } else {

            LOGGER.warn("cannot find config file {}", CONFIG_FILE_NAME);
        }

    }
}
