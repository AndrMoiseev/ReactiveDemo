package com.amoiseev.sample.demo.infra;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    public HttpServer httpServer(Vertx vertx, Router router) {
        var server = vertx.createHttpServer();
        server.requestHandler(router).listen(8888);
        return server;
    }

    @Bean
    public WebClient webClient(Vertx vertx) {
        var options = new WebClientOptions();
        options.setTrustAll(true);
        return WebClient.create(vertx, options);
    }


}
