package com.socialnetwork.sg.controller;

import com.socialnetwork.sg.config.ResponseWrapper;
import com.socialnetwork.sg.model.Post;
import com.socialnetwork.sg.service.PostService;
import com.socialnetwork.sg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity getAllPost() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(postService.getAllPost(), "Success", "Successfully fetched"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>("", "Failed", "Something went wrong"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllPostByUserId(@PathVariable Integer id) {
        try {
            userService.getUserbyID(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(postService.getAllPostByUserId(id), "Success", "Successfully fetched"));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(id, "failed", "Unable to find a user"));
        }catch (MethodArgumentTypeMismatchException e){
            userService.getUserbyID(id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(), "failed", "Bad Request"));
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity createPost(@RequestBody Post post, @PathVariable Integer userId) {
        try {
            postService.createPost(post, userId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(post, "Success", "Post Successfully Created"));
        }catch ( NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(userId, "failed", "Unable to find a user"));
        }catch (MethodArgumentTypeMismatchException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(), "failed", "Bad Request"));
        }
    }

    @GetMapping("/wall/{userId}")
    public ResponseEntity getWall(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(postService.wall(userId), "Success", "Successfully fetched"));
    }

    @PostMapping("/wall/like/{postId}")
    public ResponseEntity like(@PathVariable Integer postId, @RequestParam Integer userId) {
        postService.likeUnlike(postId, userId, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(postId, "Success", "Liked the Post"));
    }

    @PostMapping("/wall/unlike/{postId}")
    public ResponseEntity unlike(@PathVariable Integer postId, @RequestParam Integer userId) {
        postService.likeUnlike(postId, userId, Boolean.FALSE);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(postId, "Success", "Unliked the Post"));
    }
}
