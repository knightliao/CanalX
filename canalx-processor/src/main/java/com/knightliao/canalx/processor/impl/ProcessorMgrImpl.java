package com.knightliao.canalx.processor.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.dto.MysqlEntry;
import com.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.knightliao.canalx.core.exception.CanalxPluginException;
import com.knightliao.canalx.core.exception.CanalxProcessorException;
import com.knightliao.canalx.core.exception.CanalxProcessorInitException;
import com.knightliao.canalx.core.plugin.IPlugin;
import com.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.processor.IProcessorMgr;

/**
 * @author knightliao
 * @date 2016/12/1 11:52
 */
public class ProcessorMgrImpl implements IProcessorMgr, IPlugin {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessorMgrImpl.class);

    private Map<String, ICanalxProcessor> innerCanalProcessors = new LinkedHashMap<String, ICanalxProcessor>(10);

    private List<ICanalxProcessor> iCanalxProcessors = new ArrayList<>(10);

    @Override
    public void loadPlugin(String scanPack, Set<String> specifyPluginNames) throws CanalxPluginException {

        Reflections reflections = new Reflections(scanPack);
        Set<Class<? extends ICanalxProcessor>> canalProcessors = reflections.getSubTypesOf(ICanalxProcessor
                .class);

        for (Class<? extends ICanalxProcessor> canalProcessor : canalProcessors) {

            String pluginName = canalProcessor.getAnnotation(PluginName.class).name();

            if (!specifyPluginNames.contains(pluginName)) {
                continue;
            }

            LOGGER.info("loading processor: {} - {}", pluginName, canalProcessor.toString());

            try {
                Class<ICanalxProcessor> canalProcessorClass = (Class<ICanalxProcessor>) canalProcessor;

                innerCanalProcessors.put(pluginName, canalProcessorClass.newInstance());
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }

        for (String processorName : innerCanalProcessors.keySet()) {

            iCanalxProcessors.add(innerCanalProcessors.get(processorName));
        }
    }

    @Override
    public List<ICanalxProcessor> getProcessorPlugin() {
        return iCanalxProcessors;
    }

    /**
     * @param entry
     */
    @Override
    public void runProcessor(MysqlEntryWrap entry) throws CanalxProcessorException {

        List<ICanalxProcessor> iCanalxProcessors = this.getProcessorPlugin();

        for (ICanalxProcessor icanalProcessor : iCanalxProcessors) {

            MysqlEntry mysqlEntry = entry.getMysqlEntry();
            if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_INSERT) {

                LOGGER.info("run processor... {}  insert", icanalProcessor.getClass());

                icanalProcessor.processInsert(entry);

            } else if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_UPDATE) {

                LOGGER.info("run processor... {}  update", icanalProcessor.getClass());

                icanalProcessor.processUpdate(entry);

            } else if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_DELETE) {

                LOGGER.info("run processor... {} delete", icanalProcessor.getClass());

                icanalProcessor.processDelete(entry);
            }

        }
    }

    @Override
    public void init() throws CanalxProcessorInitException {

        for (ICanalxProcessor iCanalxProcessor : iCanalxProcessors) {
            iCanalxProcessor.init();
        }
    }
}
