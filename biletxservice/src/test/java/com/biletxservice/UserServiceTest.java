package com.biletxservice;

import com.biletx.configuration.BiletxUserSendMailQueue;
import com.biletx.converter.UserConverter;
import com.biletx.enums.UserType;
import com.biletx.model.User;
import com.biletx.repository.UserRepository;
import com.biletx.request.UserRequest;
import com.biletx.response.UserResponse;
import com.biletx.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserConverter converter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BiletxUserSendMailQueue biletxUserSendMailQueue;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Test
    void it_should_create() {

        // given

        Mockito.when(converter.convert(Mockito.any(UserRequest.class), Mockito.anyString())).thenReturn(new User());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());

        Mockito.when(converter.convert(Mockito.any(User.class))).thenReturn(getUserResponse());

        Mockito.when(biletxUserSendMailQueue.getQueueName()).thenReturn("queue.name");

        // when

        UserResponse userResponse = userService.createUser(getUserRequest());


        // then

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo(getUser().getName());
        assertThat(userResponse.getEmail()).isEqualTo(getUser().getEmail());
        assertThat(userResponse.getType()).isEqualTo(getUser().getType());


        verify(rabbitTemplate, times(0)).convertAndSend(Mockito.anyString(), Mockito.any(UserRequest.class));
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

}
