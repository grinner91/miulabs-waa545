package com.miu.lab2.controllers;

import com.miu.lab2.aspect.annotation.Lab4ExecutionTime;
import com.miu.lab2.aspect.annotation.Lab4LogInfo;
import com.miu.lab2.entity.dto.CommentDto;
import com.miu.lab2.entity.dto.PostDto;
import com.miu.lab2.entity.dto.UserDto;
import com.miu.lab2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Lab4ExecutionTime
    @Lab4LogInfo
    @GetMapping()
    public List<UserDto> findAll() {
        return  userService.findAll();
    }

    @Lab4ExecutionTime
    @Lab4LogInfo
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable int id) {
        return  userService.findById(id);
    }

    @Lab4LogInfo
    @GetMapping("/{id}/posts")
    public List<PostDto> findPostsById(@PathVariable int id) {
        return  userService.findPostsById(id);
    }

    @Lab4LogInfo
    @GetMapping("/posts/{count}")
    public List<UserDto> findUsersByPostsGreaterThan(@PathVariable int count) {
        return userService.findUsersByPostsCountGreaterThan(count);
    }


    @Lab4LogInfo
    //localhost:8080/api/v1/users/111/posts/1/comments/1
    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto findCommentById(@PathVariable int userId,
                                      @PathVariable int postId,
                                      @PathVariable int commentId) {

        return userService.findCommentById(userId, postId, commentId);
    }

    @Lab4LogInfo
    @PostMapping()
    public ResponseEntity<String> save(@RequestBody UserDto newUser) {
        userService.save(newUser);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Added successfully.");
    }

    @Lab4LogInfo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted...");
    }
}

