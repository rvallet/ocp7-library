package com.library.msbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config-ms-batch")
public class ApplicationPropertiesConfig {
}
