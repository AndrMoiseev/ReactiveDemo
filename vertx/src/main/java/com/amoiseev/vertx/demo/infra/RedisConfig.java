package com.amoiseev.vertx.demo.infra;

import io.vertx.core.Vertx;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisAPI redisAPI(Vertx vertx) throws InterruptedException {
        var options = new RedisOptions();
        options.setPassword("eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81");

        var client = Redis.createClient(vertx, options);
        return RedisAPI.api(client);
    }
}
