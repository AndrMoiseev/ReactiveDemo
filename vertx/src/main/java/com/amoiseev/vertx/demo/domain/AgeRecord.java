package com.amoiseev.sample.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public class AgeRecord {
    String name;
    int age;
}
