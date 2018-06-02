package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import com.acme.eshop.repository.UserRepository;
import com.acme.eshop.resources.UserResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void testShowUser() {
        //given
        Long userId = 101L;
        User mockUser = createUser();
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(mockUser));
        //when
        User result = userServiceImpl.showUser(userId);
        //then
        assertEquals(result, mockUser);
    }

    private User createUser() {
        User user = new User();
        user.setId(101L);
        user.setEmail("test@email.com");
        return user;
    }
}
