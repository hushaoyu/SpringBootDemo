package com.demo.mavenTutorial.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:dev.properties")
@ConfigurationProperties(prefix = "com.demo")
public class DevConfigBean {
    private String env;
    private String uuid;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "DevConfigBean{" +
                "env='" + env + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
