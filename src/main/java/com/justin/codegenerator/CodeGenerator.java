package com.justin.codegenerator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {
    public static void main(String[] args) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://ip:port/xxx?useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("xxx");
        dataSourceConfig.setPassword("xxx");
        dataSourceConfig.setDbQuery(new MySqlQuery());
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());

        String fileModule = "module";
        List<String> fileTables = new ArrayList<>();
        fileTables.add("xxx");
        fileTables.add("yyyy");
        generateModuleCode(fileModule, fileTables, dataSourceConfig);
    }

    private static void generateModuleCode(String module, List<String> tables, DataSourceConfig dataSourceConfig) {
        String apiTemplatePath = "";
        String serviceTemplatePath = "";
        String controllerTemplatePath = "";
        String xmlTemplatePath = "";
        apiTemplatePath = apiTemplatePath.replaceAll("module", module);
        serviceTemplatePath = serviceTemplatePath.replaceAll("module", module);
        controllerTemplatePath = controllerTemplatePath.replaceAll("module", module);
        xmlTemplatePath = xmlTemplatePath.replaceAll("module", module);
        AutoGenerator autoGenerator = new AutoGenerator();
        // global config
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setFileOverride(true);
        globalConfig.setAuthor("Justin");
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);
        autoGenerator.setGlobalConfig(globalConfig);
        // datasource config
        autoGenerator.setDataSource(dataSourceConfig);
        // package config
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.xxx.framework.xxx");
        packageConfig.setEntity(module + ".service.entity");
        packageConfig.setMapper(module + ".service.mapper");
        packageConfig.setService(module + ".service.api");
        packageConfig.setServiceImpl(module + ".service.provider");
        packageConfig.setController("bootstrap.controller." + module);
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.ENTITY_PATH, apiTemplatePath + "\\entity");
        pathInfo.put(ConstVal.MAPPER_PATH, apiTemplatePath + "\\mapper");
        pathInfo.put(ConstVal.XML_PATH, xmlTemplatePath);
        pathInfo.put(ConstVal.SERVICE_PATH, apiTemplatePath + "\\api");
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, serviceTemplatePath + "\\provider");
        pathInfo.put(ConstVal.CONTROLLER_PATH, controllerTemplatePath);
        packageConfig.setPathInfo(pathInfo);
        autoGenerator.setPackageInfo(packageConfig);
        // strategy config
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(tables.toArray(new String[0]));
        autoGenerator.setStrategy(strategyConfig);
        // injection config
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        String finalApiTemplatePath = apiTemplatePath;
        fileOutConfigList.add(new FileOutConfig("templates\\voreq.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return finalApiTemplatePath + "\\vo\\req\\" + tableInfo.getEntityName() + "Req" + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig("templates\\voresp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return finalApiTemplatePath + "\\vo\\resp\\" + tableInfo.getEntityName() + "Resp" + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig("templates\\voparam.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return finalApiTemplatePath + "\\vo\\param\\" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig("templates\\vopage.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return finalApiTemplatePath + "\\vo\\page\\" + tableInfo.getEntityName() + "Page" + StringPool.DOT_JAVA;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.execute();
    }
}
