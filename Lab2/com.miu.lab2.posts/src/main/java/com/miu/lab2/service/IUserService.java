package com.miu.lab2.service;

import com.miu.lab2.dto.PostDto;
import com.miu.lab2.dto.UserDto;
import com.miu.lab2.entity.Post;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();

    UserDto findById(int id);

    List<PostDto> findPostsById(int id);

    void save(UserDto userDto);

    void deleteById(int id);

    public List<UserDto> findUsersByPostsCountGreaterThan(int count);

}
