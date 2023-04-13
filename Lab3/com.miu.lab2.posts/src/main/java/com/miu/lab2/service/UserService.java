package com.miu.lab2.service;

import com.miu.lab2.aspect.annotation.Lab4ExecutionTime;
import com.miu.lab2.aspect.annotation.Lab4LogInfo;
import com.miu.lab2.entity.dto.CommentDto;
import com.miu.lab2.entity.dto.PostDto;
import com.miu.lab2.entity.dto.UserDto;
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

    @Lab4LogInfo
    @Override
    public List<UserDto> findAll() {

        var users = userRepository.findAll();

        var usersDto = new ArrayList<UserDto>();

        users.forEach(u -> usersDto.add(modelMapper.map(u, UserDto.class)));

        return usersDto;
    }

    @Lab4ExecutionTime
    @Override
    public UserDto findById(int id) {
        var user = userRepository.findById(id).get();
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


/*
    @GetMapping("/projects")
    public List<Project> searchProjectsByCriteria(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("projectName") String projectName,
            @RequestParam("estimatedDays") int estimatedDays,
            @RequestParam("location") String location) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);

        query.select(root)
                .distinct(true)
                .where(cb.and(
                        cb.equal(root.join("employees").get("id"), employeeId),
                        cb.like(root.get("name"), "%" + projectName + "%"),
                        cb.ge(root.get("estimatedDays"), estimatedDays),
                        cb.equal(root.get("location"), location)
                ));

        return entityManager.createQuery(query).getResultList();
    }


 */
}
