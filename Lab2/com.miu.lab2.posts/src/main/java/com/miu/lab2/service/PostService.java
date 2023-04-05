package com.miu.lab2.service;

import com.miu.lab2.dto.PostDto;
import com.miu.lab2.repository.IPostRepository;
import com.miu.lab2.entity.Post;
import com.miu.lab2.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postsRepo;
    @Autowired
    private IUserRepository userRepo;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<PostDto> findAll() {
        var posts = postsRepo.findAll();
        return posts.stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    public List<PostDto> findAllV2() {
        var posts = postsRepo.findAll();
        return posts.stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .map(p -> new PostDto(p.getId(), p.getTitle() + " v2", p.getContent(), p.getAuthor(), p.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto findPostById(int id) {
        return modelMapper.map( postsRepo.findById(id), PostDto.class);
    }

    @Override
    public boolean deletePostById(int id) {
         postsRepo.deleteById(id);
         return true;
    }

    @Override
    public boolean addPost(PostDto postDto) {
        var newPost = modelMapper.map(postDto, Post.class);
        var user = userRepo.findById(postDto.getUserId()).get();

        newPost.setUser(user);
        newPost.setCreatedDate(new Date());

         postsRepo.save(newPost);

         return true;
    }

    @Override
   public boolean updatePost(PostDto postDto) {
        var post = modelMapper.map(postDto, Post.class);
         postsRepo.save(post);
         return true;
    }
}
