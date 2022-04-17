package com.amoiseev.sample.demo.adapters.cache.impl;

import com.amoiseev.sample.demo.domain.AgeRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class AgeCacheConfig {

    @Bean
    public ReactiveRedisTemplate<String, AgeRecord> ageTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<String, AgeRecord>(
                factory,
                RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(AgeRecord.class)));
    }
}
