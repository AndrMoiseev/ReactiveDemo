package com.amoiseev.sample.demo.adapters.cache;

import com.amoiseev.sample.demo.domain.AgeRecord;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AgeCache {
    private final static String AGE_HASH_KEY = "ages";

    private final ReactiveHashOperations<String, String, AgeRecord> opsForHash;

    public AgeCache(ReactiveRedisTemplate<String, AgeRecord> template) {
        this.opsForHash = template.opsForHash();
    }

    public Mono<AgeRecord> getAgeByName(String name) {
        return opsForHash.get(AGE_HASH_KEY, name);
    }

    public Mono<Boolean> updateAge(String name, int age) {
        return opsForHash.put(name, AGE_HASH_KEY, new AgeRecord(name, age));
    }
}
