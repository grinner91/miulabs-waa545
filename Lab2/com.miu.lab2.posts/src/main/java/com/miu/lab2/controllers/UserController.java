package com.miu.lab2.controllers;

import com.miu.lab2.dto.PostDto;
import com.miu.lab2.dto.UserDto;
import com.miu.lab2.entity.Post;
import com.miu.lab2.entity.User;
import com.miu.lab2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping()
    public List<UserDto> findAll() {
        return  userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable int id) {
        return  userService.findById(id);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> findPostsById(@PathVariable int id) {
        return  userService.findPostsById(id);
    }

    @GetMapping("/posts/{count}")
    public List<UserDto> findUsersByPostsGreaterThan(@PathVariable int count) {
        return userService.findUsersByPostsCountGreaterThan(count);
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody UserDto newUser) {
        userService.save(newUser);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Added successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted...");
    }

}

