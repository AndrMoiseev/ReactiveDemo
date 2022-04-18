package com.amoiseev.vertx.demo.adapters.cache;

import com.amoiseev.sample.demo.domain.AgeRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Future;
import io.vertx.redis.client.RedisAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeCache {
    private final static String AGE_HASH_KEY = "ages";

    private final RedisAPI redis;
    private final ObjectMapper mapper;

    public Future<AgeRecord> getAgeByName(String name) {
        return redis
            .hget(AGE_HASH_KEY, name)
            .map(response -> response != null ? deserialize(response.toString()) : null);
    }

    public void updateAge(String name, int age) {
        var record = new AgeRecord(name, age);
        redis.hsetnx(name, AGE_HASH_KEY,serialize(record));
    }

    private AgeRecord deserialize(String str) {
        try {
            return mapper.readValue(str, AgeRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String serialize(AgeRecord record) {
        try {
            return mapper.writeValueAsString(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
