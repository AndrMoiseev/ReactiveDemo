package com.amoiseev.vertx.demo.adapters.age;

import com.amoiseev.sample.demo.domain.AgeRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.ext.web.client.WebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeClient {
    private static final String HOST = "api.agify.io";

    private final WebClient client;
    private final ObjectMapper mapper;

    public Future<Integer> getAgeByName(String name) {
        var promise = Promise.<Integer>promise();

        client
            .get(443, HOST, "/")
            .ssl(true)
            .addQueryParam("name", name)
            .send()
            .onSuccess(res -> {
                var ageRecord = res.bodyAsJson(AgeRecord.class);
                promise.complete(ageRecord.getAge());
            })
            .onFailure(ex -> promise.fail(ex));

        return promise.future();
    }
}
