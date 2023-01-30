package com.biletx.converter;

import com.biletx.model.Ticket;
import com.biletx.model.User;
import com.biletx.model.UserSendMail;
import com.biletx.model.UserSendSms;

import java.time.LocalDateTime;

public class MessageConverter {
    public String message(Ticket ticket) {
        return "Bilet Bilgileri : " +
                "\nBilet Id : " + ticket.getId() +
                "\nAraç tipi : " + ticket.getVehicle().getVehicleType() +
                "\nAd-Soyad : " + ticket.getPassengerName() +
                "\nCinsiyet : " + ticket.getGender() +
                "\nGüzergah : " + ticket.getVehicle().getFromWhere() +
                "-" + ticket.getVehicle().getWhereTo() +
                "\nSaat : " + ticket.getVehicle().getDepartureClock();
    }

    public UserSendSms convertSms(Ticket ticket) {
        UserSendSms userSendSms = new UserSendSms();
        userSendSms.setUser(ticket.getUser());
        userSendSms.setSendDate(LocalDateTime.now());
        userSendSms.setMessage(message(ticket));
        userSendSms.setVehicle(ticket.getVehicle());
        return userSendSms;
    }
    public UserSendMail convertMail(User user) {
        UserSendMail userSendMail = new UserSendMail();
        userSendMail.setUser(user);
        userSendMail.setSendDate(LocalDateTime.now());
        userSendMail.setMessage("Merhaba "+user.getName()+" aramıza hoşgeldin."+" Biletx müşteri kaydın başarı ile " +
                "oluşturuldu.");
        return userSendMail;
    }

}
