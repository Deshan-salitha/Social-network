package com.socialnetwork.sg.service;

import com.socialnetwork.sg.model.Role;
import com.socialnetwork.sg.model.User;
import com.socialnetwork.sg.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void getAllUsers() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
        User user = User.builder()
                .firstname("Deshan")
                .lastname("Salitha")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.salitha@outlook.com")
                .password("1234")
                .build();
        User user2 = User.builder()
                .firstname("Deshan")
                .lastname("Darshana")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.darshana@outlook.com")
                .password("1234")
                .build();
        List<User> expectedUsers = Arrays.asList(user,user2);

        Mockito.when(userRepositoryMock.findAll()).thenReturn(expectedUsers);

        UserService userService = new UserService(userRepositoryMock);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testGetUserbyID_ExistingUser() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

        User expectedUser = User.builder()
                .firstname("Deshan")
                .lastname("Salitha")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.salitha@outlook.com")
                .password("1234")
                .build();

        Mockito.when(userRepositoryMock.findById(1)).thenReturn(Optional.of(expectedUser));

        UserService userService = new UserService(userRepositoryMock);

        User actualUser = userService.getUserbyID(1);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUserbyID_NonexistentUser() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

        Mockito.when(userRepositoryMock.findById(2)).thenReturn(Optional.empty());

        UserService userService = new UserService(userRepositoryMock);

        assertThrows(NoSuchElementException.class, () -> userService.getUserbyID(2));

    }


    @Test
    void createUser() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

        User newUser = User.builder()
                .firstname("Deshan")
                .lastname("Salitha")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.salitha@outlook.com")
                .password("1234")
                .build();

        Mockito.when(userRepositoryMock.save(newUser)).thenReturn(newUser);

        UserService userService = new UserService(userRepositoryMock);

        userService.createUser(newUser);

        Mockito.verify(userRepositoryMock).save(newUser);
    }

    @Test
    void addFriend() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

        User user = User.builder()
                .firstname("Deshan")
                .lastname("Salitha")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.salitha@outlook.com")
                .password("1234")
                .build();
        User friend = User.builder()
                .firstname("Deshan")
                .lastname("Darshana")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.darshana@outlook.com")
                .password("1234")
                .build();

        Mockito.when(userRepositoryMock.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepositoryMock.findById(2)).thenReturn(Optional.of(friend));

        UserService userService = new UserService(userRepositoryMock);

        userService.addFriend(1, 2);

        Mockito.verify(userRepositoryMock).findById(1);
        Mockito.verify(userRepositoryMock).findById(2);

        assertEquals(1, user.getFriends().size());
        assertTrue(user.getFriends().contains(friend));

        // Verify userRepository.save is called with the updated user
        Mockito.verify(userRepositoryMock).save(user);
    }

    @Test
    void addFllower() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

        User user = User.builder()
                .firstname("Deshan")
                .lastname("Salitha")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.salitha@outlook.com")
                .password("1234")
                .build();
        User follower = User.builder()
                .firstname("Deshan")
                .lastname("Darshana")
                .friends(new ArrayList<>())
                .followers(new ArrayList<>())
                .role(Role.USER)
                .email("Deshan.darshana@outlook.com")
                .password("1234")
                .build();

        Mockito.when(userRepositoryMock.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepositoryMock.findById(2)).thenReturn(Optional.of(follower));

        UserService userService = new UserService(userRepositoryMock);

        userService.addFllower(1, 2);

        Mockito.verify(userRepositoryMock).findById(1);
        Mockito.verify(userRepositoryMock).findById(2);

        assertEquals(1, user.getFollowers().size());
        assertTrue(user.getFollowers().contains(follower));

        Mockito.verify(userRepositoryMock).save(user);
    }
}