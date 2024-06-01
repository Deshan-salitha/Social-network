package com.socialnetwork.sg.service;

import com.socialnetwork.sg.repository.UserRepository;
import com.socialnetwork.sg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepositoryMock) {
        this.userRepository = userRepositoryMock;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserbyID(Integer userId) {
        return userRepository.findById(userId).get();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Integer userid) {
        User existingUser = getUserbyID(userid);
        if ((!existingUser.getFirstname().equals(user.getFirstname())) && (user.getFirstname() != null))
            existingUser.setFirstname(user.getFirstname());
        if ((!existingUser.getLastname().equals(user.getLastname())) && (user.getLastname() != null))
            existingUser.setLastname(user.getLastname());
        userRepository.save(existingUser);
    }

    public void addFriend(Integer userId, Integer friendId) {
        User user = getUserbyID(userId);
        User friend = getUserbyID(friendId);
        List<User> friends = user.getFriends();
        friends.add(friend);
        user.setFriends(friends);
        userRepository.save(user);
    }

    public void addFllower(Integer userId, Integer followerId) {
        User user = getUserbyID(userId);
        User follower = getUserbyID(followerId);
        List<User> followers = user.getFollowers();
        followers.add(follower);
        user.setFollowers(followers);
        userRepository.save(user);
    }
}
