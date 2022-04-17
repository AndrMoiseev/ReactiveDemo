package com.amoiseev.sample.demo.app;

import com.amoiseev.sample.demo.adapters.age.AgeClient;
import com.amoiseev.sample.demo.adapters.cache.AgeCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AgeService {
    private final AgeClient client;
    private final AgeCache cache;

    public Mono<Integer> getAgeByName(String name) {
        return cache.getAgeByName(name)
                .map(ar -> ar.getAge())
                .switchIfEmpty(client.getAgeByName(name))
                .doOnSuccess(age -> cache.updateAge(name, age));
    }
}
