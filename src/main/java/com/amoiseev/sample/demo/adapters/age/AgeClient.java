package com.amoiseev.sample.demo.adapters.age;

import com.amoiseev.sample.demo.domain.AgeRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.ext.web.client.WebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AgeClient {
    private static final String HOST = "api.agify.io";

    private final WebClient client;
    private final ObjectMapper mapper;

    public Mono<Integer> getAgeByName(String name) {
        var future = new CompletableFuture<Integer>();

        client
            .get(443, HOST, "/")
            .ssl(true)
            .addQueryParam("name", name)
            .send()
            .onSuccess(res -> {
                var ageRecord = res.bodyAsJson(AgeRecord.class);
                future.complete(ageRecord.getAge());
            })
            .onFailure(ex -> future.completeExceptionally(ex));

        return Mono.fromCompletionStage(future);
    }
}
