package com.biletx.request;

import com.biletx.enums.PaymentType;
import com.biletx.model.CreditCard;

public class PaymentRequest {
    private CreditCard creditCard;
    private Integer userId;
    private PaymentType paymentType;

    public PaymentRequest() {
    }

    public PaymentRequest(CreditCard creditCard, Integer userId, PaymentType paymentType) {
        this.creditCard = creditCard;
        this.userId = userId;
        this.paymentType = paymentType;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "creditCard=" + creditCard +
                ", userId=" + userId +
                ", paymentType=" + paymentType +
                '}';
    }
}
