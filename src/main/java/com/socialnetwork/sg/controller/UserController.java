package com.socialnetwork.sg.controller;

import com.socialnetwork.sg.config.ResponseWrapper;
import com.socialnetwork.sg.model.User;
import com.socialnetwork.sg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseWrapper(userService.getAllUsers(), "success", "fetched"));
    }
    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(user, "Success", "Successfully Created A user"));
    }

    @PostMapping("/{userId}/friends/{friendId}")
    public ResponseEntity addFriend(@PathVariable Integer userId, @PathVariable Integer friendId) {
        userService.addFriend(userId, friendId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(userId, "Success", "Successfully added the friend"));
    }

    @PostMapping("/{userId}/followers/{followerId}")
    public ResponseEntity addfollower(@PathVariable Integer userId, @PathVariable Integer followerId) {
        userService.addFllower(userId, followerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(userId, "Success", "Successfully followed the friend"));
    }
}
