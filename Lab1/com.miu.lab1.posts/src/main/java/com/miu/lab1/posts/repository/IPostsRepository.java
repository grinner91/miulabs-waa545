package com.miu.lab1.posts.repository;

import com.miu.lab1.posts.service.PostDto;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


public interface IPostsRepository {
    public List<Post> findAllPosts();
    public Post findPostById(int id);

    public boolean deletePostById(int id);

    boolean addPost(Post post);

    boolean updatePost(Post post);

}
