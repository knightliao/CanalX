package com.github.knightliao.canalx.core.test.plugin.processor.support.transform.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.knightliao.canalx.core.dto.MysqlColumn;
import com.github.knightliao.canalx.core.dto.MysqlEntry;
import com.github.knightliao.canalx.core.plugin.processor.support.transform.EntryTransformFactory;
import com.github.knightliao.canalx.core.plugin.processor.support.transform.IEntryTransform;
import com.github.knightliao.canalx.core.plugin.processor.support.transform.TransformResult;

/**
 * @author knightliao
 * @date 2016/12/13 12:02
 */
public class InsertEntryTransformImplTestCase {

    @Test
    public void testOk() {

        IEntryTransform iEntryTransform = EntryTransformFactory.getInsertTransform();

        MysqlEntry mysqlEntry = new MysqlEntry();

        List<MysqlColumn> mysqlColumns = new ArrayList<>();
        MysqlColumn mysqlColumn = new MysqlColumn();
        mysqlColumn.setName("id");
        mysqlColumn.setValue(String.valueOf(1L));
        mysqlColumns.add(mysqlColumn);

        mysqlEntry.setColumns(mysqlColumns);

        TransformResult transformResult = iEntryTransform.entry2Json(mysqlEntry, "id");
        Assert.assertEquals(transformResult.getKey(), String.valueOf(1L));

    }

    @Test
    public void testNotOk() {

        IEntryTransform iEntryTransform = EntryTransformFactory.getInsertTransform();

        MysqlEntry mysqlEntry = new MysqlEntry();

        List<MysqlColumn> mysqlColumns = new ArrayList<>();
        MysqlColumn mysqlColumn = new MysqlColumn();
        mysqlColumn.setName("id");
        mysqlColumns.add(mysqlColumn);

        mysqlEntry.setColumns(mysqlColumns);

        TransformResult transformResult = iEntryTransform.entry2Json(mysqlEntry, "id");
        Assert.assertEquals(transformResult, null);
    }
}
