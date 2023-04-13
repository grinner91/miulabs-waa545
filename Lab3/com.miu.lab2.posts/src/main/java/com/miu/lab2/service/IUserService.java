package com.miu.lab2.service;

import com.miu.lab2.entity.dto.CommentDto;
import com.miu.lab2.entity.dto.PostDto;
import com.miu.lab2.entity.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();

    UserDto findById(int id);

    List<PostDto> findPostsById(int id);

    void save(UserDto userDto);

    void deleteById(int id);

    public List<UserDto> findUsersByPostsCountGreaterThan(int count);

    CommentDto findCommentById(int userId, int postId, int commentId);

    List<UserDto> findUsersWithPostsTitle(String title);

}
