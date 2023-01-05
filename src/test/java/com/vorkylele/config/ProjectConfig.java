package com.vorkylele.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:default.properties"})
public interface ProjectConfig extends Config {

    @Key("base.uri")
    @DefaultValue("http://qa-scooter.praktikum-services.ru")
    String getBaseUriProperties();

    @Key("base.api.path")
    @DefaultValue("/api/v1")
    String getBaseApiPathProperties();
}
