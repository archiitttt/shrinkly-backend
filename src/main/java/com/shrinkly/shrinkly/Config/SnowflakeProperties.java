package com.shrinkly.shrinkly.Config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "snowflake")
@RequiredArgsConstructor
@Getter
@Setter
@Component
public class SnowflakeProperties {

    private long workerId;
    private long datacenterId;

}
