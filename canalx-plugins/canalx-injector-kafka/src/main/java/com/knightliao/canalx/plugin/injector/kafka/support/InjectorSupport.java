package com.knightliao.canalx.plugin.injector.kafka.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.plugin.injector.template.InjectorEntryProcessTemplate;

import kafka.consumer.ConsumerIterator;
import kafka.message.MessageAndMetadata;

/**
 * @author knightliao
 * @date 2016/11/24 18:40
 */
public class InjectorSupport {

    protected static final Logger LOGGER = LoggerFactory.getLogger(InjectorSupport.class);

    private ConsumerIterator<byte[], byte[]> it;

    private Gson gson = new Gson();

    private InjectorEntryProcessTemplate injectorEntryProcessTemplate;

    public InjectorSupport(ConsumerIterator<byte[], byte[]> it,
                           InjectorEntryProcessTemplate injectorEntryProcessTemplate) {
        this.it = it;
        this.injectorEntryProcessTemplate = injectorEntryProcessTemplate;
    }

    public void processMsg() {

        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> mm = it.next();
            String message = new String(mm.message());

            // partition && offset
            long partition = mm.partition();
            long offset = mm.offset();

            MysqlEntry entry = gson.fromJson(message, MysqlEntry.class);
            if (entry.getEvent() == MysqlEntry.MYSQL_DELETE) {
                continue;
            }

            LOGGER.debug(message);

            // 计算延迟时间
            long now = System.currentTimeMillis();
            long elapsedSinceMysql = (now - entry.getTime()) / 1000;
            long elapsedSinceCanal = (now - entry.getCanalTime()) / 1000;

            String originTableName = entry.getTable();

            if (injectorEntryProcessTemplate != null) {
                injectorEntryProcessTemplate.processEntry(entry);
            }

            LOGGER.info(
                    "Succeed to import an INSERT from table {}, mysql_delay={}, "
                            + "canal_delay={}, partition={}, offset={}",
                    originTableName, elapsedSinceMysql, elapsedSinceCanal, partition, offset);
        }
    }
}
