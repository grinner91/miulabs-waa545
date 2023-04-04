package com.miu.lab1.posts.controllers;

import com.miu.lab1.posts.repository.Post;
import com.miu.lab1.posts.service.IPostsService;
import com.miu.lab1.posts.service.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    private IPostsService postsService;

    @GetMapping()
    public List<PostDto> findAllPosts() {
        return postsService.findAllPosts();
    }

    @GetMapping(headers = {"api-version=v2"})
    public List<PostDto> findAllPostsV2() {
        return  postsService.findAllPostsV2();
    }

    @GetMapping("/{id}")
    public PostDto findPostById(@PathVariable int id) {
        return postsService.findPostById(id);
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostDto newPost) {
        if(postsService.addPost(newPost))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully added.");
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Failed to add." );
    }

    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable int id) {
        return postsService.deletePostById(id);
    }

    @PutMapping("/{id}")
    public boolean updatePost(@PathVariable int id, @RequestBody PostDto updatedPost) {
        updatedPost.setId(id);
        return postsService.updatePost(updatedPost);
    }

}
