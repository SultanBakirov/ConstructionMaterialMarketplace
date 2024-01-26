package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.PasswordChangeRequest;
import com.example.constructionmaterialmarketplace.dto.request.UserRequest;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.dto.response.UserResponse;
import com.example.constructionmaterialmarketplace.entity.User;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public UserResponse getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getUserById(user.getId());
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUserData(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @PutMapping("/password/{userId}")
    public SimpleResponse changeUserPassword(@PathVariable Long userId, @RequestBody PasswordChangeRequest passwordChangeRequest) {
        return userService.changeUserPassword(userId, passwordChangeRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses).getBody();
    }

    @DeleteMapping("/{userId}")
    public SimpleResponse deleteUser(@PathVariable Long userId) throws NotFoundException {
        return userService.deleteUser(userId);
    }
}
