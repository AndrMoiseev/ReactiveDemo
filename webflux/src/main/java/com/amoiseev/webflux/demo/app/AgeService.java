package com.amoiseev.webflux.demo.app;

import com.amoiseev.webflux.demo.adapters.age.AgeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AgeService {
    private final AgeClient client;

    public Mono<Integer> getAgeByName(String name) {
        return client.getAgeByName(name);
    }
}
