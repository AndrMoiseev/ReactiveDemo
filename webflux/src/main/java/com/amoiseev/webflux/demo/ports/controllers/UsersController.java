package com.amoiseev.webflux.demo.ports.controllers;

import com.amoiseev.webflux.demo.adapters.dao.UsersDao;
import com.amoiseev.webflux.demo.model.CreateOrUpdateUserRequest;
import com.amoiseev.webflux.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersDao dao;

    @GetMapping("")
    public Flux<User> listUsers() {
        return dao.listUsers();
    }

    @GetMapping("/{userId}")
    public Mono<User> getUserById(@PathVariable String userId) {
        return dao.getUserById(userId);
    }

    @PostMapping("")
    public Mono<User> createUser(@RequestBody CreateOrUpdateUserRequest request) {
        return dao.createUser(request);
    }

    @PutMapping("/{userId}")
    public Mono<User> updateUser(@PathVariable String userId, @RequestBody CreateOrUpdateUserRequest request) {
        return dao.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteUser(@PathVariable String userId) {
        return dao.deleteUserById(userId);
    }
}
