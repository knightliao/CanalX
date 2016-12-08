package com.github.knightliao.canalx.plugin.processor.kv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.plugin.router.ICanalxDataRouter;
import com.github.knightliao.canalx.core.support.annotation.EntryFilterList;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.plugin.processor.kv.data.CanalxKvInstance;
import com.github.knightliao.canalx.plugin.processor.kv.support.filter.EntryTimeFilter;
import com.github.knightliao.canalx.plugin.processor.kv.support.transform.EntryTransformFactory;
import com.github.knightliao.canalx.plugin.processor.kv.support.transform.IEntryTransform;
import com.github.knightliao.canalx.plugin.processor.kv.support.transform.TransformResult;
import com.github.knightliao.canalx.plugin.processor.kv.support.TableTopicUtil;

/**
 * @author knightliao
 * @date 2016/11/23 18:21
 */
@PluginName(name = "canalx-processor-kv")
@EntryFilterList(classes = {EntryTimeFilter.class})
public class CanalxKvProcessor implements ICanalxProcessor, ICanalxDataRouter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CanalxKvProcessor.class);

    private String fileName = "canalx-db-kv.xml";

    // transform
    private IEntryTransform insertTransform = EntryTransformFactory.getInsertTransform();
    private IEntryTransform updateTransform = EntryTransformFactory.getUpdateTransform();

    @Override
    public void processUpdate(MysqlEntryWrap entry) throws CanalxProcessorException {

        LOGGER.info(entry.toString());

        String tableId = TableTopicUtil.getTableId(entry);

        String tableKey = CanalxKvInstance.getTableKey(tableId);

        if (tableKey.equals("")) {
            LOGGER.error("cannot find tableKey for tableId {} with insert op.", tableId);
        } else {

            TransformResult transformResult = updateTransform.entry2Json(entry.getMysqlEntry(), tableKey);
            CanalxKvInstance.put(tableId, transformResult.getKey(), transformResult.getValue());
        }

    }

    @Override
    public void processInsert(MysqlEntryWrap entry) throws CanalxProcessorException {

        LOGGER.info(entry.toString());

        String tableId = TableTopicUtil.getTableId(entry);

        String tableKey = CanalxKvInstance.getTableKey(tableId);

        if (tableKey.equals("")) {
            LOGGER.error("cannot find tableKey for tableId {} with update op. ", tableId);
        } else {

            TransformResult transformResult = insertTransform.entry2Json(entry.getMysqlEntry(), tableKey);
            CanalxKvInstance.put(tableId, transformResult.getKey(), transformResult.getValue());
        }

    }

    @Override
    public void processDelete(MysqlEntryWrap entry) throws CanalxProcessorException {

        // not process
        LOGGER.info(entry.toString());
    }

    /**
     * 初始化
     */
    @Override
    public void init() {

        LOGGER.info("start to init {}", CanalxKvProcessor.class.toString());
        CanalxKvInstance.init(fileName);
    }

    /**
     * @param tableId
     * @param key
     *
     * @return
     */
    @Override
    public String get(String tableId, String key) {

        return CanalxKvInstance.get(tableId, key);
    }
}
