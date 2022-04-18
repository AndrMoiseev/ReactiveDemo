package com.amoiseev.webflux.demo.adapters.dao.impl;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UsersRepository extends ReactiveMongoRepository<UserDocument, String> {
}
