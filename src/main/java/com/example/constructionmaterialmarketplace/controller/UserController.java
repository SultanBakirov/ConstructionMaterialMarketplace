package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.entity.User;
import com.example.constructionmaterialmarketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> findAllUser() {
        List<User> users = userService.findAllUser();
        return users;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
