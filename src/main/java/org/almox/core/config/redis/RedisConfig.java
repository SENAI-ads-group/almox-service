package org.almox.core.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient getRedissonClient(Environment environment) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(environment.getProperty("redis-almox.url"));
        return Redisson.create(config);
    }
}
