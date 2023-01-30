package com.biletx.listener;

import com.biletx.model.Ticket;
import com.biletx.model.User;
import com.biletx.model.UserSendMail;
import com.biletx.repository.UserSendSmsRepository;
import com.biletx.converter.MessageConverter;
import com.biletx.model.UserSendSms;
import com.biletx.repository.UserSendMailRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class NotificationListener {
    @Autowired
    private UserSendSmsRepository userSendSmsRepository;
    @Autowired
    private UserSendMailRepository userSendMailRepository;
    @Autowired
    private MessageConverter messageConverter;

    @RabbitListener(queues = "biletx.notification.userSendSms")
    public void payment(Ticket ticket) {
        UserSendSms userSendSms = messageConverter.convertSms(ticket);
        userSendSmsRepository.save(userSendSms);
    }

   @RabbitListener(queues = "biletx.notification.userSendLoginEmail")
    public void notificationListener(User user) {
       UserSendMail userSendMail = messageConverter.convertMail(user);
       userSendMailRepository.save(userSendMail);
       Logger logger=new  Logger(NotificationListener.class);
        System.out.println("User kuyruktan okundu.");
    }


}
