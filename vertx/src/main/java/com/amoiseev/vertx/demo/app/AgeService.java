package com.amoiseev.vertx.demo.app;

import com.amoiseev.vertx.demo.adapters.age.AgeClient;
import com.amoiseev.vertx.demo.adapters.cache.AgeCache;
import io.vertx.core.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeService {
    private final AgeClient client;
    private final AgeCache cache;

    public Future<Integer> getAgeByName(String name) {
        return cache.getAgeByName(name)
                .flatMap(maybeAge -> {
                    if (maybeAge == null) {
                        return client
                                .getAgeByName(name)
                                .onSuccess(age -> cache.updateAge(name, age));
                    } else {
                        return Future.succeededFuture(maybeAge.getAge());
                    }
                });

    }
}
