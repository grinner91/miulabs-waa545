package com.miu.lab1.posts.service;

import java.util.List;

public interface IPostsService {
    public List<PostDto> findAllPosts();
    List<PostDto> findAllPostsV2();
    public PostDto findPostById(int id);
    public boolean deletePostById(int id);
    public boolean addPost(PostDto postDto);

    boolean updatePost(PostDto postDto);
}
