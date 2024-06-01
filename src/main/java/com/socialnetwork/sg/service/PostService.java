package com.socialnetwork.sg.service;


import com.socialnetwork.sg.model.User;
import com.socialnetwork.sg.model.Post;
import com.socialnetwork.sg.model.enumerations.Visibility;
import com.socialnetwork.sg.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserService userService;

    public List<Post> getAllPost() {
        return postRepo.findAll();
    }

    public List<Post> getAllPostByUserId(Integer id) {
        return postRepo.findPostByCreatedBy_Id(id);
    }

    public Optional<Post> getPostById(Integer id) {
        return postRepo.findById(id);
    }


    public void createPost(Post post, Integer userId) {
        User auther = userService.getUserbyID(userId);
        post.setCreatedBy(auther);

        LocalDateTime postedOn = LocalDateTime.now();
        post.setPostedOn(postedOn);
        postRepo.save(post);
    }

    public List<Post> wall(Integer userId) {
        User user = userService.getUserbyID(userId);
        Set<User> userList = Stream.concat(user.getFriends().stream(), user.getFollowers().stream()).collect(Collectors.toSet());
        userList.add(user);
        List<Integer> idList = userList.stream().map(User::getId).collect(Collectors.toList());
        List<Post> wall = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            wall.addAll(getAllPostByUserId(idList.get(i)));
        }
        List<Post> sortedWall = wall.stream()
                .sorted(Comparator.comparing(Post::getPostedOn).reversed())
                .collect(Collectors.toList());
        List<Post> filteredWall = sortedWall.stream().filter(post -> post.getVisibility().equals(Visibility.PUBLIC)).collect(Collectors.toList());
        return filteredWall;
    }

    public void likeUnlike(Integer postId, Integer userId, Boolean isLiked) {
        Post post = getPostById(postId).get();
        User user = userService.getUserbyID(userId);
        List<User> liked = post.getLikes();
        if (isLiked){
            liked.add(user);
        } else{
            if (liked.stream().anyMatch(user1 -> user1.equals(user))){
                liked.remove(user);
            }
        }
        post.setLikes(liked);
        postRepo.save(post);
    }
}
