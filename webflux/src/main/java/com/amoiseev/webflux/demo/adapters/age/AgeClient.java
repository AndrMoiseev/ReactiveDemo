package com.amoiseev.webflux.demo.adapters.age;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AgeClient {
    private static final String HOST = "api.agify.io";

    private final WebClient client;
    private final ObjectMapper mapper;

    public Mono<Integer> getAgeByName(String name) {

        Mono<AgeRecord> result =  client
            .get()
            .uri(uriBuilder -> uriBuilder
                    .scheme("HTTPS")
                    .host(HOST)
                    .port(443)
                    .queryParam("name", name)
                    .build())
            .exchangeToMono(response -> {
                if (response.statusCode().is2xxSuccessful()) {
                    return response.bodyToMono(AgeRecord.class);
                } else {
                    return response.createException().flatMap(Mono::error);
                }
            });

        return result.map(ar -> ar.getAge());
    }
}
