package com.miu.lab2.service;

import com.miu.lab2.entity.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    List<CommentDto> findAll();
    CommentDto findById(int id);

    void save(CommentDto commentDto);

    void deleteById(int id);
}
