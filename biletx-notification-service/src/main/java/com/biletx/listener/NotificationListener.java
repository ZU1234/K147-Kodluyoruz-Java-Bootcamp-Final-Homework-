package com.biletx.listener;


import com.biletx.converter.MessageConverter;
import com.biletx.model.Ticket;
import com.biletx.model.UserSendMail;
import com.biletx.model.UserSendSms;
import com.biletx.repository.UserSendMailRepository;
import com.biletx.repository.UserSendSmsRepository;
import com.biletx.request.UserRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class NotificationListener {
    @Autowired
    private UserSendSmsRepository userSendSmsRepository;
    @Autowired
    private UserSendMailRepository userSendMailRepository;
    @Autowired
    private MessageConverter messageConverter;
    private final Logger logger = Logger.getLogger(NotificationListener.class.getName());


    @RabbitListener(queues = "biletx.notification.userSendSms")
    public void SendSms(Ticket ticket) {
        UserSendSms userSendSms = messageConverter.convertSms(ticket);
        userSendSmsRepository.save(userSendSms);
        logger.log(Level.INFO,"[SendSms] - user id :{0} sms gönderildi.",ticket.getUser().getId());
    }

    @RabbitListener(queues = "biletx.notification.userSendLoginMail")
    public void sendMail(UserRequest userRequest) {
        UserSendMail userSendMail = messageConverter.convertMail(userRequest);
        userSendMailRepository.save(userSendMail);
        logger.log(Level.INFO,"[SendMail] - user email : {0} kişisine mail gönderildi.",userRequest.getEmail());
    }


}
