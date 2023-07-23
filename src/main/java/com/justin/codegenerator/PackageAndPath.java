package com.justin.codegenerator;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PackageAndPath {
    private String controllerPath;

    private String servicePath;

    private String serviceImplPath;

    private String mapperPath;

    private String xmlPath;

    private String entityPath;

    private String voPath;

    private String controllerPackage;

    private String servicePackage;

    private String serviceImplPackage;

    private String mapperPackage;

    private String entityPackage;
}
