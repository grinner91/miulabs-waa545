package com.miu.lab2.service;

import com.miu.lab2.dto.CommentDto;
import com.miu.lab2.entity.Comment;
import com.miu.lab2.repository.ICommentRepository;
import com.miu.lab2.repository.IPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IPostRepository postRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<CommentDto> findAll() {
        var comments = commentRepository.findAll();
        var dtos = new ArrayList<CommentDto>();

        comments.forEach(c -> {
            var newDto = modelMapper.map(c, CommentDto.class);
            newDto.setPostId(c.getPost().getId());
            dtos.add(newDto);
        });

        return dtos;
    }

    @Override
    public CommentDto findById(int id) {
        return  modelMapper.map(
                commentRepository.findById(id).get(),
                CommentDto.class
        );
    }

    @Override
    public void save( CommentDto commentDto) {
        var newComment = modelMapper.map(commentDto, Comment.class);
        var post = postRepository.findById(commentDto.getPostId()).get();
        newComment.setPost(post);
        commentRepository.save(newComment);
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}
