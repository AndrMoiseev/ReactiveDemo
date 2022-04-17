package com.amoiseev.sample.demo.adapters.dao;

import com.amoiseev.sample.demo.adapters.dao.impl.UserDocument;
import com.amoiseev.sample.demo.adapters.dao.impl.UsersRepository;
import com.amoiseev.sample.demo.model.CreateOrUpdateUserRequest;
import com.amoiseev.sample.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersDao {
    private final UsersRepository repository;

    public Flux<User> listUsers() {
        return repository.findAll()
                .map(ud -> convert(ud));
    }

    public Mono<User> getUserById(String userId) {
        return repository.findById(userId)
                .map(ud -> convert(ud));
    }

    public Mono<Void> deleteUserById(String userId) {
        return repository.deleteById(userId);
    }

    public Mono<User> createUser(CreateOrUpdateUserRequest request) {
        var userDocument = new UserDocument(UUID.randomUUID().toString(), request.getName());
        return repository.insert(userDocument)
                .map(ud -> convert(ud));
    }

    public Mono<User> updateUser(String userId, CreateOrUpdateUserRequest request) {
        var userDocument = new UserDocument(userId, request.getName());
        return repository.save(userDocument)
                .map(ud -> convert(ud));
    }

    private static User convert(UserDocument userDocument) {
        return new User(userDocument.getId(), userDocument.getName());
    }
}
