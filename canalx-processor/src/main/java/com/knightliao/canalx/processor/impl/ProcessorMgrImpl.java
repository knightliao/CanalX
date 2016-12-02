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
import com.knightliao.canalx.core.plugin.IPlugin;
import com.knightliao.canalx.core.plugin.injector.ICanalInjector;
import com.knightliao.canalx.core.plugin.processor.ICanalProcessor;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.processor.IProcessorMgr;

/**
 * @author knightliao
 * @date 2016/12/1 11:52
 */
public class ProcessorMgrImpl implements IProcessorMgr, IPlugin {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessorMgrImpl.class);

    private Map<String, ICanalProcessor> innerCanalProcessors = new LinkedHashMap<String, ICanalProcessor>(10);

    @Override
    public void loadPlugin(String scanPack, Set<String> specifyPluginNames) {

        Reflections reflections = new Reflections(scanPack);
        Set<Class<? extends ICanalInjector>> canalInjectors = reflections.getSubTypesOf(ICanalInjector
                .class);

        //
        //  load processor
        //
        Set<Class<? extends ICanalProcessor>> canalProcessors = reflections.getSubTypesOf(ICanalProcessor
                .class);

        for (Class<? extends ICanalProcessor> canalProcessor : canalProcessors) {

            String pluginName = canalProcessor.getAnnotation(PluginName.class).name();

            if (!specifyPluginNames.contains(pluginName)) {
                continue;
            }

            LOGGER.info("loading processor: {} - {}", pluginName, canalProcessor.toString());

            try {
                Class<ICanalProcessor> canalProcessorClass = (Class<ICanalProcessor>) canalProcessor;

                innerCanalProcessors.put(pluginName, canalProcessorClass.newInstance());
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
    }

    @Override
    public List<ICanalProcessor> getProcessorPlugin() {

        List<ICanalProcessor> iCanalProcessors = new ArrayList<>(10);

        for (String processorName : innerCanalProcessors.keySet()) {

            iCanalProcessors.add(innerCanalProcessors.get(processorName));
        }

        return iCanalProcessors;
    }

    /**
     * @param entry
     */
    @Override
    public void runProcessor(MysqlEntryWrap entry) {

        List<ICanalProcessor> iCanalProcessors = this.getProcessorPlugin();

        for (ICanalProcessor icanalProcessor : iCanalProcessors) {

            MysqlEntry mysqlEntry = entry.getMysqlEntry();
            if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_INSERT) {

                LOGGER.info("run processor... {}  insert", icanalProcessor.getClass());

                icanalProcessor.processInsert(mysqlEntry);

            } else if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_UPDATE) {

                LOGGER.info("run processor... {}  update", icanalProcessor.getClass());

                icanalProcessor.processUpdate(mysqlEntry);

            } else if (mysqlEntry.getEvent() == MysqlEntry.MYSQL_DELETE) {

                LOGGER.info("run processor... {} delete", icanalProcessor.getClass());

                icanalProcessor.processDelete(mysqlEntry);
            }

        }
    }
}
