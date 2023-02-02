package com.biletx.converter;

import com.biletx.model.Ticket;
import com.biletx.model.UserSendMail;
import com.biletx.model.UserSendSms;
import com.biletx.request.UserRequest;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.time.LocalDateTime;

@Component
public class MessageConverter {
    private String message(Ticket ticket) {
        return "Bilet Bilgileri : " +
                "Bilet Id : " + ticket.getId() +
                "Araç tipi : " + ticket.getVehicle().getVehicleType() +
                "Ad-Soyad : " + ticket.getPassengerName() +
                "Cinsiyet : " + ticket.getGender() +
                "Güzergah : " + ticket.getVehicle().getFromWhere() +
                "-" + ticket.getVehicle().getWhereTo() +
                "Saat : " + ticket.getVehicle().getDepartureClock();
    }

    public UserSendSms convertSms(Ticket ticket) {
        UserSendSms userSendSms = new UserSendSms();
        userSendSms.setUserId(ticket.getUser().getId());
        userSendSms.setSendDate(LocalDateTime.now());
        userSendSms.setMessage(message(ticket));
        userSendSms.setVehicleId(ticket.getVehicle().getId());
        return userSendSms;
    }

    public  UserSendMail convertMail(UserRequest request) {
        UserSendMail userSendMail = new UserSendMail();
        userSendMail.setUserEmail(request.getEmail());
        userSendMail.setSendDate(LocalDateTime.now());
        userSendMail.setMessage("Merhaba " + request.getName() + " aramıza hoşgeldin." + " Biletx müşteri kaydın " +
                "başarı ile " +
                "oluşturuldu.");
        return userSendMail;
    }

}
