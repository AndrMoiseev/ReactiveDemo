package com.amoiseev.sample.demo.ports.vertx;

import com.amoiseev.sample.demo.app.AgeService;
import com.amoiseev.sample.demo.model.AgeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VertxAgeController {

    private final ObjectMapper mapper;
    private final AgeService service;

    /**
     * GET /age?name=foo
     * @param ctx Routing context
     */
    public void getAgeByName(RoutingContext ctx) {
        var name = ctx.queryParam("name").get(0);
        service.getAgeByName(name)
                .doOnError(ex -> {
                    ctx.request().response().setStatusCode(500);
                    ctx.request().response().end("500 Server Error");
                })
                .doOnSuccess(age -> {
                    try {
                        var responseStr = mapper.writeValueAsString(new AgeResponse(name, age));

                        ctx.request().response().setStatusCode(200);
                        ctx.request().response().headers().add("Content-Type", "application/json");
                        ctx.request().response().end(responseStr);
                    } catch (JsonProcessingException e) {
                        ctx.request().response().setStatusCode(500);
                        ctx.request().response().end("500 Server Error");
                    }
                });
    }
}
