package com.amoiseev.sample.demo.adapters.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class UserDocument {
    @Id
    String id;
    String name;
}
