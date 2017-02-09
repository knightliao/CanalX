package com.github.knightliao.canalx.plugin.injector.kafka.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.plugin.injector.template.InjectorEventProcessTemplate;
import com.github.knightliao.canalx.plugin.injector.kafka.CanalxKafkaInjector;
import com.google.gson.Gson;

import kafka.consumer.ConsumerIterator;
import kafka.message.MessageAndMetadata;

/**
 * @author knightliao
 * @date 2016/11/24 18:40
 */
public class InjectorSupport implements Runnable {

    protected static final Logger LOGGER = LoggerFactory.getLogger(InjectorSupport.class);

    private ConsumerIterator<byte[], byte[]> it;

    private Gson gson = new Gson();

    private String topic;

    private InjectorEventProcessTemplate injectorEventProcessTemplate;

    public InjectorSupport(ConsumerIterator<byte[], byte[]> it, String topic,
                           InjectorEventProcessTemplate injectorEventProcessTemplate) {
        this.it = it;
        this.topic = topic;
        this.injectorEventProcessTemplate = injectorEventProcessTemplate;
    }

    @Override
    public void run() {

        LOGGER.info("start to run Injector{} for Topic{}", CanalxKafkaInjector.class.toString(), topic);

        while (it.hasNext()) {

            try {

                MessageAndMetadata<byte[], byte[]> mm = it.next();
                String message = new String(mm.message());

                // partition && offset
                long partition = mm.partition();
                long offset = mm.offset();

                MysqlEntry entry = gson.fromJson(message, MysqlEntry.class);

                // warp
                MysqlEntryWrap mysqlEntryWrap = new MysqlEntryWrap(topic, entry);

                LOGGER.debug(message);

                // 计算延迟时间
                long now = System.currentTimeMillis();
                long elapsedSinceMysql = (now - entry.getTime()) / 1000;
                long elapsedSinceCanal = (now - entry.getCanalTime()) / 1000;

                String originTableName = entry.getTable();

                if (injectorEventProcessTemplate != null) {
                    injectorEventProcessTemplate.processEntry(mysqlEntryWrap);
                }

                LOGGER.info(
                        "Topic({}) Succeed to do Event{} inject from Table{}, mysql_delay={}, "
                                + "canal_delay={}, partition={}, offset={}",
                        topic,
                        entry.getEvent(),
                        originTableName, elapsedSinceMysql, elapsedSinceCanal, partition, offset);

            } catch (Throwable e) {

                LOGGER.error(e.toString());
            }
        }

    }
}
