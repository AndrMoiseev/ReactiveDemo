package com.amoiseev.sample.demo.ports.webflux;

import com.amoiseev.sample.demo.app.AgeService;
import com.amoiseev.sample.demo.model.AgeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/age")
@RequiredArgsConstructor
public class WebfluxAgeController {

    private final AgeService service;

    @GetMapping("")
    public Mono<AgeResponse> getAgeByName(@RequestParam String name) {
        return service.getAgeByName(name)
                .map(age -> new AgeResponse(name, age));
    }
}
