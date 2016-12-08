package com.github.knightliao.canalx.processor.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.knightliao.canalx.core.dto.MysqlEntryWrap;
import com.github.knightliao.canalx.core.exception.CanalxPluginException;
import com.github.knightliao.canalx.core.exception.CanalxProcessorException;
import com.github.knightliao.canalx.core.exception.CanalxProcessorInitException;
import com.github.knightliao.canalx.core.plugin.IPlugin;
import com.github.knightliao.canalx.core.plugin.processor.EntryFilterChainFactory;
import com.github.knightliao.canalx.core.plugin.processor.ICanalxProcessor;
import com.github.knightliao.canalx.core.plugin.processor.IEntryFilter;
import com.github.knightliao.canalx.core.plugin.processor.IEntryFilterChain;
import com.github.knightliao.canalx.core.support.annotation.EntryFilterList;
import com.github.knightliao.canalx.core.support.annotation.PluginName;
import com.github.knightliao.canalx.core.support.context.ICanalxContext;
import com.github.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.github.knightliao.canalx.core.support.context.IDataSourceAware;
import com.github.knightliao.canalx.core.support.reflection.ReflectionUtil;
import com.github.knightliao.canalx.processor.IProcessorMgr;
import com.github.knightliao.canalx.processor.impl.chain.ICanalxProcessorAware;
import com.github.knightliao.canalx.processor.impl.chain.ProcessCoreFilter;

/**
 * @author knightliao
 * @date 2016/12/1 11:52
 */
public class ProcessorMgrImpl implements IProcessorMgr, IPlugin {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessorMgrImpl.class);

    private Map<String, ICanalxProcessor> innerCanalProcessors = new LinkedHashMap<String, ICanalxProcessor>(10);

    // entry filter
    private Map<String, IEntryFilterChain> innerCanalEntryFilterMap = new HashMap<>();

    //
    private List<ICanalxProcessor> iCanalxProcessors = new ArrayList<>(10);

    // context
    private ICanalxContext iCanalxContext = null;

    @Override
    public void loadPlugin(String scanPack, Set<String> specifyPluginNames) throws CanalxPluginException {

        Reflections reflections = ReflectionUtil.getReflection(scanPack);
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

                ICanalxProcessor iCanalxProcessor = canalProcessorClass.newInstance();

                // load filter
                loadEntryFilterList(pluginName, canalProcessorClass, iCanalxProcessor);

                innerCanalProcessors.put(pluginName, iCanalxProcessor);
            } catch (Exception e) {
                LOGGER.error(e.toString(), e);
            }
        }

        for (String processorName : innerCanalProcessors.keySet()) {

            iCanalxProcessors.add(innerCanalProcessors.get(processorName));
        }

        if (iCanalxContext instanceof IDataSourceAware) {
            ((IDataSourceAware) iCanalxContext).setDataSource(this.innerCanalProcessors);
        }
    }

    /**
     * 载入filter
     *
     * @param canalProcessorClass
     */
    private void loadEntryFilterList(String pluginName, Class<ICanalxProcessor> canalProcessorClass, ICanalxProcessor
            iCanalxProcessor) {

        // get filter list
        EntryFilterList entryFilterList = canalProcessorClass.getAnnotation(EntryFilterList.class);
        Class<?>[] classes = entryFilterList.classes();
        List<Class<?>> classList = new ArrayList<>(Arrays.asList(classes));

        // add core
        classList.add(ProcessCoreFilter.class);

        List<IEntryFilter> iEntryFilters = new ArrayList<>();

        for (Class<?> curClass : classList) {

            try {

                IEntryFilter iEntryFilter = (IEntryFilter) curClass.newInstance();

                // core filter
                if (iEntryFilter instanceof ICanalxProcessorAware) {
                    ((ICanalxProcessorAware) iEntryFilter).setupICanalxProcessor(iCanalxProcessor);
                }

                iEntryFilters.add(iEntryFilter);

            } catch (Exception e) {
                LOGGER.error(e.toString(), e);
            }
        }

        IEntryFilterChain iEntryFilterChain = EntryFilterChainFactory.getEntryFilterChain(iEntryFilters);
        innerCanalEntryFilterMap.put(pluginName, iEntryFilterChain);
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

        for (String pluginName : innerCanalEntryFilterMap.keySet()) {

            // filter
            IEntryFilterChain iEntryFilterChain = innerCanalEntryFilterMap.get(pluginName);
            iEntryFilterChain.doFilter(entry);
        }
    }

    @Override
    public void init() throws CanalxProcessorInitException {

        for (ICanalxProcessor iCanalxProcessor : iCanalxProcessors) {

            if (iCanalxProcessor instanceof ICanalxContextAware) {
                ((ICanalxContextAware) iCanalxProcessor).setCanalxContext(iCanalxContext);
            }

            iCanalxProcessor.init();
        }
    }

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.iCanalxContext = iCanalxContext;

        if (iCanalxContext instanceof IDataSourceAware) {
            ((IDataSourceAware) iCanalxContext).setDataSource(this.innerCanalProcessors);
        }
    }
}
