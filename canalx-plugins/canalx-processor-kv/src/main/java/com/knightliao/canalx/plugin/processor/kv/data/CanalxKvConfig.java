package com.knightliao.canalx.plugin.processor.kv.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import lombok.Data;

/**
 * @author knightliao
 * @date 2016/11/28 18:07
 */
@Data
public class CanalxKvConfig {

    private String driverClass;
    private String dbUrl;
    private String userName;
    private String password;
    private String initSql;

    public void setupConfig() throws IOException {

        Properties kvProperty = new Properties();
        URL url = CanalxKvInstance.class.getClassLoader().getResource("canalx.properties");
        kvProperty.load(new InputStreamReader(new FileInputStream(url.getPath()),
                "utf-8"));

        driverClass = kvProperty.getProperty("canalx.plugin.kv.driverClass");
        dbUrl = kvProperty.getProperty("canalx.plugin.kv.url");
        userName = kvProperty.getProperty("canalx.plugin.kv.username");
        password = kvProperty.getProperty("canalx.plugin.kv.password");
        initSql = kvProperty.getProperty("canalx.plugin.kv.initSql");
    }
}
