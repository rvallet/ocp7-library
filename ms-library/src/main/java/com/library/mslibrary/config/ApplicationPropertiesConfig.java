package com.library.mslibrary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config-ms-library")
public class ApplicationPropertiesConfig {

    private int pageSizeLimit;

    public int getPageSizeLimit() {
        return pageSizeLimit;
    }

    public void setPageSizeLimit(int pageSizeLimit) {
        this.pageSizeLimit = pageSizeLimit;
    }
}
