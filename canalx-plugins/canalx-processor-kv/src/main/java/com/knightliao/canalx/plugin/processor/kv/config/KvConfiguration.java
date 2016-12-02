package com.knightliao.canalx.plugin.processor.kv.config;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class KvConfiguration {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigParser.class);

    String jdbcDriver;

    Map<String, TableConfig> allTableInfo;

    public static void parse(URL xmlPath) throws Exception {
        parse(xmlPath.openStream());
    }

    public static void parse(InputStream inputStream) throws Exception {

        KvConfiguration genConfiguration = new ConfigParser().parse(inputStream);

        LOGGER.debug(genConfiguration.toString());
    }

}
