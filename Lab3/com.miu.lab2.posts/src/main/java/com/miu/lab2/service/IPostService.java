package com.miu.lab2.service;

import com.miu.lab2.dto.PostDto;
import com.miu.lab2.dto.UserDto;
import com.miu.lab2.entity.Post;

import java.util.List;

public interface IPostService {
    public List<PostDto> findAll();
    List<PostDto> findAllV2();
    public PostDto findPostById(int id);
    public boolean deletePostById(int id);
    public boolean addPost(PostDto postDto);

    boolean updatePost(PostDto postDto);

    List<PostDto> findPostsByTitleIgnoreCase(String title);


}
