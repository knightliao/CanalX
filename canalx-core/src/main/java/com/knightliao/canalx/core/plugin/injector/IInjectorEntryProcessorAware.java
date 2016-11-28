package com.knightliao.canalx.core.plugin.injector;

import com.knightliao.canalx.core.plugin.injector.template.InjectorEntryProcessTemplate;

/**
 * entry process aware
 *
 * @author knightliao
 * @date 2016/11/24 11:46
 */
public interface IInjectorEntryProcessorAware {

    void setupProcessEntry(InjectorEntryProcessTemplate injectorEntryProcessTemplate);
}
