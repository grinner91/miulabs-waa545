package com.miu.lab2.repository;

import com.miu.lab2.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPostRepository extends CrudRepository<Post, Integer> {
    public List<Post> findAll();
//    public Post findPostById(int id);
//
//    public boolean deletePostById(int id);
//
//
//    boolean updatePost(Post post);

}
