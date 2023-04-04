package com.miu.lab1.posts.service;

import com.miu.lab1.posts.repository.IPostsRepository;
import com.miu.lab1.posts.repository.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsService implements IPostsService{
    @Autowired
    private IPostsRepository postsRepo;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<PostDto> findAllPosts() {
        var posts = postsRepo.findAllPosts();
        return posts.stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    public List<PostDto> findAllPostsV2() {
        var posts = postsRepo.findAllPosts();
        return posts.stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .map(p -> new PostDto(p.getId(), p.getTitle() + " v2", p.getContent(), p.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto findPostById(int id) {
        return modelMapper.map( postsRepo.findPostById(id), PostDto.class);
    }

    @Override
    public boolean deletePostById(int id) {
        return postsRepo.deletePostById(id);
    }

    @Override
    public boolean addPost(PostDto postDto) {
        var newPost = modelMapper.map(postDto, Post.class);
        var id = postsRepo.findAllPosts().size() + 1;

        newPost.setId(id);
        newPost.setCreatedDate(new Date());

        return postsRepo.addPost(newPost);
    }

    @Override
   public boolean updatePost(PostDto postDto) {
        var post = modelMapper.map(postDto, Post.class);
        return postsRepo.updatePost(post);
    }
}
