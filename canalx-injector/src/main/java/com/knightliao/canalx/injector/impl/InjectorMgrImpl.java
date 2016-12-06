package com.knightliao.canalx.injector.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knightliao.canalx.core.exception.CanalxInjectorException;
import com.knightliao.canalx.core.exception.CanalxInjectorInitException;
import com.knightliao.canalx.core.plugin.IPlugin;
import com.knightliao.canalx.core.plugin.injector.ICanalxInjector;
import com.knightliao.canalx.core.plugin.injector.IInjectorEntryProcessorAware;
import com.knightliao.canalx.core.plugin.injector.template.IInjectEntryProcessCallback;
import com.knightliao.canalx.core.plugin.injector.template.InjectorEntryProcessTemplate;
import com.knightliao.canalx.core.support.annotation.PluginName;
import com.knightliao.canalx.core.support.context.ICanalxContext;
import com.knightliao.canalx.core.support.context.ICanalxContextAware;
import com.knightliao.canalx.injector.InjectorMgr;

/**
 * @author knightliao
 * @date 2016/12/1 11:34
 */
public class InjectorMgrImpl implements InjectorMgr, IPlugin {

    protected static final Logger LOGGER = LoggerFactory.getLogger(InjectorMgrImpl.class);

    // injector
    private Map<String, ICanalxInjector> innerCanalInjectors = new LinkedHashMap<String, ICanalxInjector>(10);

    //
    private ICanalxInjector firstInjector = null;

    // context
    private ICanalxContext iCanalxContext = null;

    /**
     * load plugins
     *
     * @param scanPack
     * @param specifyPluginNames
     */
    @Override
    public void loadPlugin(String scanPack, Set<String> specifyPluginNames) {

        Reflections reflections = new Reflections(scanPack);
        Set<Class<? extends ICanalxInjector>> canalInjectors = reflections.getSubTypesOf(ICanalxInjector
                .class);

        boolean isFirst = true;
        for (Class<? extends ICanalxInjector> canalInjector : canalInjectors) {

            String pluginName = canalInjector.getAnnotation(PluginName.class).name();

            if (!specifyPluginNames.contains(pluginName)) {
                continue;
            }

            LOGGER.info("loading injector: {} - {}", pluginName, canalInjector.toString());

            try {
                Class<ICanalxInjector> canalInjectorClass = (Class<ICanalxInjector>) canalInjector;

                innerCanalInjectors.put(pluginName, canalInjectorClass.newInstance());

                if (isFirst) {
                    firstInjector = innerCanalInjectors.get(pluginName);
                    isFirst = false;
                }

            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
    }

    /**
     * @return
     */
    @Override
    public List<ICanalxInjector> getInjectorPlugin() {

        List<ICanalxInjector> iCanalInjectors = new ArrayList<>(10);

        for (String processorName : innerCanalInjectors.keySet()) {

            iCanalInjectors.add(innerCanalInjectors.get(processorName));
        }

        return iCanalInjectors;
    }

    /**
     * @param
     */
    @Override
    public void runInjector() throws CanalxInjectorException {

        if (firstInjector != null) {

            // run
            firstInjector.run();
        }
    }

    @Override
    public void init(IInjectEntryProcessCallback injectEntryProcessCallback) throws CanalxInjectorInitException {

        if (firstInjector != null) {

            if (firstInjector instanceof IInjectorEntryProcessorAware) {
                ((IInjectorEntryProcessorAware) firstInjector).setupProcessEntry(new InjectorEntryProcessTemplate
                        (injectEntryProcessCallback));
            }

            if (firstInjector instanceof ICanalxContextAware) {
                ((ICanalxContextAware) firstInjector).setCanalxContext(iCanalxContext);
            }

            // init
            firstInjector.init();
        }

    }

    @Override
    public void setCanalxContext(ICanalxContext iCanalxContext) {
        this.iCanalxContext = iCanalxContext;
    }
}
