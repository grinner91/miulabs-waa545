package com.miu.lab2.service;

import com.miu.lab2.dto.CommentDto;
import com.miu.lab2.dto.PostDto;
import com.miu.lab2.dto.UserDto;
import com.miu.lab2.entity.User;
import com.miu.lab2.repository.IPostRepository;
import com.miu.lab2.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements  IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<UserDto> findAll() {

        var users = userRepository.findAll();

        var usersDto = new ArrayList<UserDto>();

        users.forEach(u -> usersDto.add(modelMapper.map(u, UserDto.class)));

        return usersDto;
    }

    @Override
    public UserDto findById(int id) {
        var user = userRepository.findById(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<PostDto> findPostsById(int id) {
        var posts =  userRepository
                .findById(id)
                .get()
                .getPosts();

        return  posts.stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public void save(UserDto userDto) {
        var newUser = modelMapper.map(userDto, User.class);
        userRepository.save(newUser);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> findUsersByPostsCountGreaterThan(int count) {
        //TODO need to fix bug
        var users = userRepository.findUsersWithPosts(count);
        if(users != null) {
            return users.stream()
                    .map(u -> modelMapper.map(u, UserDto.class))
                    .toList();
        }
        else {
            return new ArrayList<UserDto>();
        }
    }

    @Override
    public CommentDto findCommentById(int userId, int postId, int commentId) {
        var user = userRepository
                .findById(userId)
                .get();

        var post = user
                .getPosts()
                .stream()
                .filter(p -> p.getId() == postId)
                .findFirst()
                .get();

        var comm  = post
                .getComments()
                .stream()
                .filter(c -> c.getId() == commentId)
                .findFirst()
                .get();

        return modelMapper.map(comm, CommentDto.class);
    }

    @Override
    public List<UserDto> findUsersWithPostsTitle(String title) {
        var users = new ArrayList<UserDto>();
        var posts = postRepository.findPostsByTitleIgnoreCase(title);
        posts.stream()
                .forEach(
                        p -> users.add(modelMapper.map(p.getUser(), UserDto.class))
                );

        return users
                .stream()
                .distinct()
                .toList();
    }
}
