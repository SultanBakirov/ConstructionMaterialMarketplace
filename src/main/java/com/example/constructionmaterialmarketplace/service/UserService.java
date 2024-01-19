package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAllUser();

    User saveUser(User user);

    User getUserById(Long userId);

    User updateUser(User user);

}
