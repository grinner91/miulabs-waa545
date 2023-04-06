//package com.miu.lab2.repository;
//
//import com.miu.lab2.entity.Post;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class PostRepository {
//
//    private static List<Post> postsList = createPosts();
//
//    private static List<Post> createPosts() {
//        var posts = new ArrayList<Post>();
//        posts.add(new Post(1, "Lab2 Post1", "This is 1st content", "Monir Zaman ", new Date()));
//        posts.add(new Post(2, "Lab2 Post12", "This is 2nd content", "Rafsan Zaman ", new Date()));
//        posts.add(new Post(3, "Lab2 Post3", "This is 3rd content", "Monir Zaman ", new Date()));
//        posts.add(new Post(4, "Lab2 Post4", "This is 4th content", "Rafsan Zaman ", new Date()));
//
//        return posts;
//    }
//
//    @Override
//    public List<Post> findAllPosts() {
//        return postsList;
//    }
//
//    @Override
//    public Post findPostById(int id) {
//        return postsList.stream()
//                .filter(p -> p.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public boolean deletePostById(int id) {
//        var prevCount = postsList.size();
//
//        postsList = (ArrayList<Post>) postsList.stream()
//                .filter(p -> p.getId() != id)
//                .collect(Collectors.toList());
//
//        if (postsList.size() < prevCount)
//            return true;
//        else
//            return false;
//    }
//
//    @Override
//    public boolean addPost(Post post) {
//        return postsList.add(post);
//    }
//
//
//   public boolean updatePost(Post updatedPost) {
//
//        var oldPost = postsList.stream()
//                .filter(post -> post.getId() == updatedPost.getId())
//                .findFirst()
//                .get();
//
//        //update other properties
//        updatedPost.setCreatedDate(oldPost.getCreatedDate());
//
//        //remove old post
//       var filteredList = postsList
//                .stream()
//                .filter(p -> p.getId() != oldPost.getId())
//                .toList();
//
//        //add updated post
//       postsList = new ArrayList<>(filteredList);
//       postsList.add(updatedPost);
//
//        return   true;
//    }
//
//}
