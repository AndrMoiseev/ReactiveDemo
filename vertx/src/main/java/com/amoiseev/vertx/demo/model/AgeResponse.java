package com.amoiseev.vertx.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public class AgeResponse {
    String name;
    int age;
}
