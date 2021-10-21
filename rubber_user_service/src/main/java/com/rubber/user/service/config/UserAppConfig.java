package com.rubber.user.service.config;

import com.rubber.base.components.redis.RedisClientProxy;
import com.rubber.base.components.redis.client.LocalCacheClientProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Configuration
public class UserAppConfig {


    @Bean
    public RedisClientProxy jedisClientProxy(){
        return new LocalCacheClientProxy();
    }

}
