package com.amoiseev.vertx.demo.infra;

import com.amoiseev.vertx.demo.ports.vertx.VertxAgeController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class VertxRouter {
    private final VertxAgeController ageController;

    @Bean
    public Router router(Vertx vertx) {
        var router =  Router.router(vertx);

        router.route(HttpMethod.GET, "/age")
                .handler(ctx -> ageController.getAgeByName(ctx));

        return router;
    }
}
