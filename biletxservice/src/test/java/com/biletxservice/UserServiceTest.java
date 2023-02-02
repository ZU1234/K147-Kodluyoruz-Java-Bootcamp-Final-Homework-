package com.biletxservice;

import com.biletx.configuration.RabbitMQConfiguration;
import com.biletx.converter.UserConverter;
import com.biletx.enums.UserType;
import com.biletx.exception.UserDoesNotException;
import com.biletx.model.User;
import com.biletx.repository.UserRepository;
import com.biletx.request.LoginRequest;
import com.biletx.request.UserRequest;
import com.biletx.response.UserResponse;
import com.biletx.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = UserService.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserConverter converter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RabbitMQConfiguration rabbitMQConfiguration;

    @Mock
    private RabbitTemplate rabbitTemplate;


    @Test
    @Before(value = "create")
    void it_should_create() throws UserDoesNotException {

        // given

        Mockito.when(converter.convert(Mockito.any(UserRequest.class), anyString())).thenReturn(new User());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());

        Mockito.when(converter.convert(Mockito.any(User.class))).thenReturn(getUserResponse());

        Mockito.when(rabbitMQConfiguration.getQueueName()).thenReturn("queue.name");

        // when

        UserResponse userResponse = userService.createUser(getUserRequest());


        // then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo(getUser().getName());
        assertThat(userResponse.getEmail()).isEqualTo(getUser().getEmail());
        assertThat(userResponse.getType()).isEqualTo(getUser().getType());


        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), Mockito.any(UserRequest.class));
        verify(userRepository).save(Mockito.any(User.class));
    }

    private User getUser() {
        return new User("test1", "test1@gmail.com", "hashPassword", UserType.INDIVIDUAL, "testphone");
    }

    private UserRequest getUserRequest() {
        return new UserRequest("test1", "test1@gmail.com", "test123", UserType.INDIVIDUAL, "testphone");
    }

    private UserResponse getUserResponse() {
        return new UserResponse("test1", "test1@gmail.com", UserType.INDIVIDUAL);
    }

    private LoginRequest getLoginRequest() {
        return new LoginRequest("test1@gmail.com", "test123");
    }
}
