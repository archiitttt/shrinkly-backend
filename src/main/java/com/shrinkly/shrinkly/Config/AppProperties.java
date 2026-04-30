package com.shrinkly.shrinkly.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String domain;
}
