package com.library.msbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config-ms-batch")
public class ApplicationPropertiesConfig {

    private String exemple;

    public String getExemple() {
        return exemple;
    }

    public void setExemple(String exemple) {
        this.exemple = exemple;
    }
}
