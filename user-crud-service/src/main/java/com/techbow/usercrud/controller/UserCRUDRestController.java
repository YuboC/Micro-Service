package com.techbow.usercrud.controller;

import com.techbow.usercrud.model.User;
import com.techbow.usercrud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserCRUDRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    List<User> getAllUsers() {
        log.info("Get all users from CRUD service");
        return userService.getAllUsers();
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        log.info("CreatE user: " + user.toString());
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        log.info("Get user: " + id);
        return userService.getUser(id);
    }

    @PutMapping
    User updateUser(@RequestBody User user) {
        log.info("update");
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
