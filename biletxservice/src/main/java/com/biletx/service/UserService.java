package com.biletx.service;

import com.biletx.client.PaymentServiceClient;
import com.biletx.configuration.BiletxUserSendMailQueue;
import com.biletx.converter.UserConverter;
import com.biletx.enums.UserType;
import com.biletx.exception.UserDoesNotException;
import com.biletx.model.Basket;
import com.biletx.model.Ticket;
import com.biletx.model.User;
import com.biletx.repository.BasketRepository;
import com.biletx.repository.TicketRepository;
import com.biletx.repository.UserRepository;
import com.biletx.repository.VehicleRepository;
import com.biletx.request.LoginRequest;
import com.biletx.request.PaymentRequest;
import com.biletx.request.UserRequest;
import com.biletx.response.PaymentResponse;
import com.biletx.response.UserResponse;
import com.biletx.util.PasswordUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service

public class UserService {
    public static final String LOGIN_SUCCESSFUL = "Giriş işlemi başarılı";
    public static final String lOGIN_INCORRECT = "Giriş bilgileri eksik yada hatalıdır.";
    private final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private PaymentServiceClient paymentServiceClient;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BiletxUserSendMailQueue biletxUserSendMailQueue;


    public UserResponse createUser(UserRequest userRequest) {

        try {

            String hash = PasswordUtil.preparePasswordHash(userRequest.getPassword(), userRequest.getEmail());
            User savedUser = userRepository.save(userConverter.convert(userRequest, hash));
            logger.log(Level.INFO, "[createUser] - user created: {0}", savedUser);

            login(new LoginRequest(userRequest.getEmail(), userRequest.getPassword()));
            //rabbitTemplate.convertAndSend(biletxUserSendMailQueue.getQueueName(), savedUser.getEmail());
            return userConverter.convert(savedUser);
        } catch (Exception e) {

            logger.log(Level.WARNING, "email : {0} zaten kayıtlı!", userRequest.getEmail());
            throw new UserDoesNotException("Bu email kullanıcısı kayıtlı!");
        }

    }


    public List<UserResponse> getAll() {
        return userConverter.convert(userRepository.findAll());
    }

    public User getById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            logger.log(Level.WARNING, "email : {0} zaten kayıtlı!", userId);
            throw new UserDoesNotException("User id : " + userId + "does " + "not exist.");
        });
    }

    // Kullanıcı tüm biletlerini listeler
    public List<Ticket> getAllTicket(int userId) {
        return ticketRepository.findAll().stream().filter(user -> userId == user.getId()).toList();
    }


    // Kullanıcı individual mi konrol eder
    public boolean isIndividual(int userId) {
        return UserType.INDIVIDUAL.equals(getById(userId).getType());
    }

    // Kullanıcı corporate mi konrol eder
    public boolean isCorporate(int userId) {
        return UserType.CORPORATE.equals(getById(userId).getType());
    }

    // Kullanıcının sepetindeki tüm biletleri listeler
    public List<Basket> getSepet(Integer userId) {
        return basketRepository.findAll().stream().filter(s -> userId.equals(s.getUserId())).toList();
    }

    // Sepete kayıt eder
    public void addTicketInBasket(Basket basket) {
        basketRepository.save(basket);
        logger.log(Level.INFO, "Sepete bilet eklendi.", basket);
    }


    public PaymentResponse payment(PaymentRequest paymentRequest) throws UserDoesNotException {
        return paymentServiceClient.payment(paymentRequest);
    }


    public String login(LoginRequest loginRequest) {
        String passwordHash="";
        String foundUser="";
        if (userRepository.findByEmail(loginRequest.getEmail()).isPresent()) {
            foundUser = userRepository.findByEmail(loginRequest.getEmail()).get().getPassword();
            passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(),
                    loginRequest.getEmail());

        }


        boolean isValid = PasswordUtil.validatePassword(passwordHash, foundUser);
        return isValid ? LOGIN_SUCCESSFUL : lOGIN_INCORRECT;
    }
}
