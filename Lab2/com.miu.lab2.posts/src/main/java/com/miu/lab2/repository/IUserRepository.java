package com.miu.lab2.repository;

import com.miu.lab2.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

//    @Query( "select p from Product p where count(p.reviews) >= :num" )
//    public List<Product> findProductWithReviews(int num);
    @Query( "select u from User u where count(u.posts) >= :count" )
    public List<User> findUsersWithPosts(int count);
}