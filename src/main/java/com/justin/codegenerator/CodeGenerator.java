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
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
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
        dataSourceConfig.setUrl("jdbc:mysql://192.168.1.101:3306/kitchen-system?useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("TiMpWfM5-");
        dataSourceConfig.setDbQuery(new MySqlQuery());
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());

        List<String> fileTables = new ArrayList<>();
        fileTables.add("recipes");
        PackageAndPath packageAndPath = new PackageAndPath();
        packageAndPath.setEntityPackage("kitchen.recipe.management.entity")
            .setMapperPackage("kitchen.recipe.management.mapper")
            .setServiceImplPackage("kitchen.recipe.management.service.impl")
            .setServicePackage("kitchen.recipe.management.service")
            .setControllerPackage("kitchen.recipe.management.controller")
            .setVoPath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\java\\com\\justin\\kitchen\\recipe\\management\\vo")
            .setEntityPath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\java\\com\\justin\\kitchen\\recipe\\management\\entity")
            .setMapperPath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\java\\com\\justin\\kitchen\\recipe\\management\\mapper")
            .setServiceImplPath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\java\\com\\justin\\kitchen\\recipe\\management\\service\\impl")
            .setServicePath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\java\\com\\justin\\kitchen\\recipe\\management\\service")
            .setControllerPath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\java\\com\\justin\\kitchen\\recipe\\management\\controller")
            .setXmlPath("D:\\workspace\\git resource\\kitchen-system\\recipe-management\\src\\main\\resources\\mappers");

        generateModuleCode("com.justin", fileTables, dataSourceConfig, packageAndPath);
    }

    private static void generateModuleCode(String basePackage, List<String> tables, DataSourceConfig dataSourceConfig, PackageAndPath packageAndPath) {
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
        packageConfig.setParent(basePackage);
        packageConfig.setController(packageAndPath.getControllerPackage());
        packageConfig.setService(packageAndPath.getServicePackage());
        packageConfig.setServiceImpl(packageAndPath.getServiceImplPackage());
        packageConfig.setMapper(packageAndPath.getMapperPackage());
        packageConfig.setEntity(packageAndPath.getEntityPackage());
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.ENTITY_PATH, packageAndPath.getEntityPath());
        pathInfo.put(ConstVal.MAPPER_PATH, packageAndPath.getMapperPath());
        pathInfo.put(ConstVal.XML_PATH, packageAndPath.getXmlPath());
        pathInfo.put(ConstVal.SERVICE_PATH, packageAndPath.getServicePath());
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, packageAndPath.getServiceImplPath());
        pathInfo.put(ConstVal.CONTROLLER_PATH, packageAndPath.getControllerPath());
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
        fileOutConfigList.add(new FileOutConfig("templates\\voreq.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return packageAndPath.getVoPath() + "\\req\\" + tableInfo.getEntityName() + "Req" + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig("templates\\voresp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return packageAndPath.getVoPath() + "\\resp\\" + tableInfo.getEntityName() + "Resp" + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig("templates\\voparam.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return packageAndPath.getVoPath() + "\\param\\" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig("templates\\vopage.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return packageAndPath.getVoPath() + "\\page\\" + tableInfo.getEntityName() + "Page" + StringPool.DOT_JAVA;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.execute();
    }
}
