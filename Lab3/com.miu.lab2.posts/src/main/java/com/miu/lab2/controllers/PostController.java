package com.miu.lab2.controllers;

import com.miu.lab2.aspect.annotation.Lab4ExecutionTime;
import com.miu.lab2.aspect.annotation.Lab4LogInfo;
import com.miu.lab2.service.IPostService;
import com.miu.lab2.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postsService;

    @Lab4ExecutionTime
    @Lab4LogInfo
    @GetMapping()
    public List<PostDto> findAllPosts() throws Exception {
        //try{
            //throw new Exception("This lab4 exception to test AOP...");
            return postsService.findAll();
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return null;
    }

    @Lab4LogInfo
    @GetMapping(headers = {"api-version=v2"})
    public List<PostDto> findAllPostsV2() {
        return  postsService.findAllV2();
    }

    @Lab4LogInfo
    @GetMapping("/title/{title}")
    public List<PostDto> findPostsByTitle(@PathVariable String title) {
        return postsService.findPostsByTitleIgnoreCase(title);
    }

    @Lab4LogInfo
    @GetMapping("/{id}")
    public PostDto findPostById(@PathVariable int id) {
        return postsService.findPostById(id);
    }

    @Lab4LogInfo
    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostDto newPost) {
        if(postsService.addPost(newPost))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully added.");
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Failed to add." );
    }

    @Lab4LogInfo
    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable int id) {
        return postsService.deletePostById(id);
    }

    @Lab4LogInfo
    @PutMapping("/{id}")
    public boolean updatePost(@PathVariable int id, @RequestBody PostDto updatedPost) {
        updatedPost.setId(id);
        return postsService.updatePost(updatedPost);
    }

}
