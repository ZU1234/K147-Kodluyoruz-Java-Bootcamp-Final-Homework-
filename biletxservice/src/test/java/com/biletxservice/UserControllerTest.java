package com.biletxservice;

import com.biletx.client.enums.CreditCardType;
import com.biletx.client.enums.PaymentType;
import com.biletx.client.model.CreditCard;
import com.biletx.controller.UserController;
import com.biletx.enums.UserType;
import com.biletx.request.LoginRequest;
import com.biletx.request.PaymentRequest;
import com.biletx.request.UserRequest;
import com.biletx.response.PaymentResponse;
import com.biletx.response.UserResponse;
import com.biletx.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Month;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class, UserService.class})
public class UserControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void it_should_get_all_users() throws Exception {
        // given
        Mockito.when(userService.getAll()).thenReturn(getAllUserResponses());

        // when
        ResultActions resultActions = mockMvc.perform(get("/users"));

        // then
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        //// @formatter:off

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].type").value(UserType.INDIVIDUAL.toString()))
                .andExpect(jsonPath("$[0].email").value("test@gmail.com"));


        // @formatter:on


    }

    @Test
    void it_should_create() throws Exception {

        Mockito.when(userService.createUser(Mockito.any(UserRequest.class)))
                .thenReturn(getUserResponse());

        String request = mapper.writeValueAsString(getUserRequest());

        ResultActions resultActions = mockMvc
                .perform(post("/users").content(request).contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.type").value(UserType.INDIVIDUAL.toString()));

    }

    @Test
    void it_should_payment() throws Exception {


        Mockito.when(userService.payment(Mockito.any(PaymentRequest.class)))
                .thenReturn(getPaymentResponse());


        String request = mapper.writeValueAsString(getPaymentRequest());

        ResultActions resultActions = mockMvc
                .perform(post("/users/payment").content(request).contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Odeme islemi basarili."))
                .andExpect(jsonPath("$.httpStatus").value("OK"));


    }

    @Test
    void it_should_login() throws Exception {


        Mockito.when(userService.login(Mockito.any(LoginRequest.class)))
                .thenReturn(getLoginResponse());


        String request = mapper.writeValueAsString(getLoginRequest());

        ResultActions resultActions = mockMvc
                .perform(post("/users/login").content(request).contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().string("Giriş yaptınız."))
                .andReturn();


    }

    private LoginRequest getLoginRequest() {
        return new LoginRequest("test@gmail.com", "test123");
    }

    private String getLoginResponse() {
        return "Giriş işlemi başarılı";
    }

    private UserRequest getUserRequest() {
        return new UserRequest("test", "test@gmail.com", "test123", UserType.INDIVIDUAL,
                "1234567890");
    }

    private List<UserResponse> getAllUserResponses() {
        return List.of(getUserResponse());
    }

    private UserResponse getUserResponse() {
        return new UserResponse("test", "test@gmail.com", UserType.INDIVIDUAL);
    }

    private PaymentRequest getPaymentRequest() {

        CreditCard creditCard = new CreditCard("testCardNumber", CreditCardType.Visa, "testcvv", "testUser",
                2025, Month.AUGUST);
        return new PaymentRequest(creditCard, 1, PaymentType.EFT);
    }

    private PaymentResponse getPaymentResponse() {
        return new PaymentResponse("Odeme islemi basarili.", HttpStatus.OK);
    }
}
