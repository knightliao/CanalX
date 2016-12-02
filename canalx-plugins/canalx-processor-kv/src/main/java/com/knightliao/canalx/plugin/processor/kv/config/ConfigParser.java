package com.knightliao.canalx.plugin.processor.kv.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 */
public class ConfigParser {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigParser.class);

    private KvConfiguration genConfiguration = new KvConfiguration();

    public KvConfiguration parse(InputStream xmlPath) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");

        Document document = null;
        DocumentBuilder docBuilder = null;
        docBuilder = factory.newDocumentBuilder();
        DefaultHandler handler = new DefaultHandler();
        docBuilder.setEntityResolver(handler);
        docBuilder.setErrorHandler(handler);

        document = docBuilder.parse(xmlPath);

        Element rootEl = document.getDocumentElement();

        NodeList children = rootEl.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;

                if (elementNameMatch(element, "base")) {

                    BaseConfig baseConfig = parseBase(element);
                    genConfiguration.setJdbcDriver(baseConfig.getDriverClass());

                } else if (elementNameMatch(element, "dbs")) {

                    Map<String, TableConfig> allTableConfigMap = parseDbs(element);
                    genConfiguration.setAllTableInfo(allTableConfigMap);
                }

            }
        }

        return genConfiguration;
    }

    /**
     * @param el
     */
    private BaseConfig parseBase(Element el) {
        BaseConfig baseConfig = new BaseConfig();
        NodeList children = el.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                if (elementNameMatch(element, "driverClass")) {
                    baseConfig.setDriverClass(element.getTextContent().trim());
                }
            }
        }

        return baseConfig;
    }

    /**
     * @param el
     *
     * @return
     */
    private DbConfig getDbConfig(Element el) {

        DbConfig dbConfig = new DbConfig();
        dbConfig.setDbName(el.getAttribute("name"));
        dbConfig.setDbUrl(el.getAttribute("dbUrl"));
        dbConfig.setUserName(el.getAttribute("userName"));
        dbConfig.setPassword(el.getAttribute("password"));

        return dbConfig;
    }

    /**
     * @param el
     */
    private Map<String, TableConfig> parseDbs(Element el) {

        // get
        DbConfig dbConfig = getDbConfig(el);

        Map<String, TableConfig> allTableConfigMap = new HashMap<>();

        NodeList children = el.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                if (elementNameMatch(element, "db")) {
                    Map<String, TableConfig> tableConfigMap = parseDb(el, dbConfig);
                    allTableConfigMap.putAll(tableConfigMap);
                }
            }
        }

        return allTableConfigMap;
    }

    /**
     * @param el
     * @param parentDbConfig
     *
     * @return
     */
    private Map<String, TableConfig> parseDb(Element el, DbConfig parentDbConfig) {

        DbConfig currentDbConfig = getDbConfig(el);

        Map<String, TableConfig> tableMap = new HashMap<>();

        NodeList children = el.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;

                if (elementNameMatch(element, "table")) {

                    TableConfig tableConfig = parseTable(el, parentDbConfig, currentDbConfig);
                    tableMap.put(tableConfig.getIdentify(), tableConfig);
                }
            }
        }

        return tableMap;
    }

    /**
     * @param el
     */
    private TableConfig parseTable(Element el, DbConfig parentDbConfig, DbConfig currentDbConfig) {

        TableConfig tableConfig = new TableConfig();

        // self config
        tableConfig.setInitSql(el.getAttribute("initSql"));
        tableConfig.setTableName(el.getAttribute("name"));

        // parent base config
        if (currentDbConfig.getDbName() != null) {
            tableConfig.setDbName(parentDbConfig.getDbName());
        } else {
            tableConfig.setDbName(currentDbConfig.getDbName());
        }

        if (currentDbConfig.getDbUrl() != null) {
            tableConfig.setDbUrl(parentDbConfig.getDbName());
        } else {
            tableConfig.setDbUrl(currentDbConfig.getDbName());
        }

        if (currentDbConfig.getUserName() != null) {
            tableConfig.setUserName(parentDbConfig.getDbName());
        } else {
            tableConfig.setUserName(currentDbConfig.getDbName());
        }

        if (currentDbConfig.getPassword() != null) {
            tableConfig.setPassword(parentDbConfig.getDbName());
        } else {
            tableConfig.setPassword(currentDbConfig.getDbName());
        }

        tableConfig.genIdentify();

        return tableConfig;
    }

    private static boolean nodeNameMatch(Node node, String desiredName) {
        return (desiredName.equals(node.getNodeName()) || desiredName.equals(node.getLocalName()));
    }

    private static boolean elementNameMatch(Node node, String desiredName) {
        return (node instanceof Element && nodeNameMatch(node, desiredName));
    }

}
