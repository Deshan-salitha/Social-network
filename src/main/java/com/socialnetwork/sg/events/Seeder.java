package com.socialnetwork.sg.events;


import com.socialnetwork.sg.model.Role;
import com.socialnetwork.sg.model.User;
import com.socialnetwork.sg.model.Post;
import com.socialnetwork.sg.model.enumerations.Visibility;
import com.socialnetwork.sg.service.PostService;
import com.socialnetwork.sg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder {
    @Autowired
    private UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private PostService postService;


    @EventListener
    public void seed(ContextRefreshedEvent event) {
        try {
            seedUsers();
            seedPosts();
        } catch (Exception e) {
            System.out.println("this is the error");
            System.out.println(e.getMessage());
        }
    }

    private void seedPosts() {
        Post post = Post.builder()
                .text("Hi network! Started to work as software Engineer at Musalasoft!")
                .visibility(Visibility.PUBLIC)
                .postedOn(LocalDateTime.now())
                .likes(new ArrayList<>())
                .build();
        postService.createPost(post, 1);
        Post post1 = Post.builder()
                .text("Hi network! Promoted as senior software Engineer at Musalasoft!")
                .visibility(Visibility.PUBLIC)
                .postedOn(LocalDateTime.now())
                .likes(new ArrayList<>())
                .build();
        postService.createPost(post1, 1);
        Post post2 = Post.builder()
                .text("Hi network! I'm happy to announce to day is 3rd work anniversary at Musalasoft!")
                .visibility(Visibility.PUBLIC)
                .postedOn(LocalDateTime.now())
                .likes(new ArrayList<>())
                .build();
        postService.createPost(post2, 1);
    }

    private void seedUsers() {
        List<User> friends = new ArrayList<>();
        List<User> followers = new ArrayList<>();
        User user = User.builder()
                .firstname("Deshan")
                .lastname("Salitha")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.salitha@outlook.com")
                .password(passwordEncoder.encode("1234"))
                .build();
        userService.createUser(user);
        friends.add(user);
        User user1 = User.builder()
                .firstname("Damith")
                .lastname("Dulshan")
                .friends(friends)
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Damith@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();
        userService.createUser(user1);
        followers.add(user);
        followers.add(user1);
        User user2 = User.builder()
                .firstname("Ishini")
                .lastname("Dharshika")
                .friends(friends)
                .followers(followers)
                .email("Darshika@gmail.com")
                .role(Role.USER)
                .password(passwordEncoder.encode("1234"))
                .build();
        userService.createUser(user2);
        User user3 = User.builder()
                .firstname("Dhananjali")
                .lastname("H")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .email("dhana@gmail.com")
                .role(Role.USER)
                .password(passwordEncoder.encode("1234"))
                .build();
        userService.createUser(user3);
//        System.out.println(user2.toString()+" "+user.toString()+" "+user1.toString());
    }
}
