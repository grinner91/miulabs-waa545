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

//    @Query( value = "select u from User u where count(u.posts) >= :count" )
//    public List<User> findUsersWithPosts(int count);

    @Query(value = "SELECT * FROM users u WHERE u.id IN (SELECT p.user_id FROM posts p GROUP BY p.user_id HAVING count(*) > :count)", nativeQuery = true)
    public List<User> findUsersWithPosts(int count);


   User findByEmail(String email);


}
