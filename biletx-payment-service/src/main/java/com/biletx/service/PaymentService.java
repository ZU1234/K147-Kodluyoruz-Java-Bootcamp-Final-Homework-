package com.biletx.service;

import com.biletx.configuration.BiletxUserSendSmsQueue;
import com.biletx.exception.BasketDoesNotException;
import com.biletx.exception.UserDoesNotException;
import com.biletx.model.Basket;
import com.biletx.model.CreditCard;
import com.biletx.model.Ticket;
import com.biletx.model.User;
import com.biletx.repository.BasketRepository;
import com.biletx.repository.TicketRepository;
import com.biletx.repository.UserRepository;
import com.biletx.repository.VehicleRepository;
import com.biletx.request.PaymentRequest;
import com.biletx.response.PaymentResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private BiletxUserSendSmsQueue biletxUserSendSmsQueue;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserRepository userRepository;

    //ödeme işlemi
    public PaymentResponse payment(PaymentRequest paymentRequest) {
        System.out.println("user id : " + paymentRequest.getUserId());
        System.out.println(basketRepository.countFindAllByUserId(paymentRequest.getUserId()));
        if (basketRepository.countFindAllByUserId(paymentRequest.getUserId()) == 0) {
            throw new BasketDoesNotException("Sepette ürün bulunmamaktadır. Lütfen önce ürün ekleyiniz.");
        }
        Integer userId = paymentRequest.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserDoesNotException("user bulunamadi."));

        PaymentResponse paymentResponse = new PaymentResponse();

        if (isPayment(paymentRequest.getCreditCard())) {
            basketRepository.findAllByUserId(userId)
                    .forEach(obj -> {
                        System.out.println(obj);
                        Ticket ticket = convert(user, obj);
                        obj.getVehicle().setEmptySeat(obj.getVehicle().getEmptySeat() - 1);
                        ticketRepository.save(ticket);
                        System.out.println(ticket);
                        vehicleRepository.save(obj.getVehicle());

                        //rabbitTemplate.convertAndSend(biletxUserSendSmsQueue.getQueueName(), ticket);
                        basketRepository.delete(obj);
                    });


            paymentResponse.setMessage("Odeme islemi basarili.");
            paymentResponse.setHttpStatus(HttpStatus.OK);
            return paymentResponse;
        }
        paymentResponse.setMessage("Banka onay vermedi");
        paymentResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return paymentResponse;
    }

    private Ticket convert(User user, Basket obj) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setGender(obj.getGender());
        ticket.setVehicle(obj.getVehicle());
        ticket.setPassengerName(obj.getPassengerName());
        ticket.setPrice(obj.getVehicle().getPrice());
        return ticket;
    }

    //ödeme işlemi kontrolü
    public Boolean isPayment(CreditCard creditCard) {
        return true;
    }
}
