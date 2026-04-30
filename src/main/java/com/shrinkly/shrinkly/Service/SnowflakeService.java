package com.shrinkly.shrinkly.Service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.shrinkly.shrinkly.Config.SnowflakeProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnowflakeService {

    private final SnowflakeProperties props;
    private Snowflake snowflake;

    @PostConstruct
    private void init(){
        this.snowflake = IdUtil.getSnowflake(props.getWorkerId(), props.getDatacenterId());
    }

    public long nextId(){
        return snowflake.nextId();
    }


}