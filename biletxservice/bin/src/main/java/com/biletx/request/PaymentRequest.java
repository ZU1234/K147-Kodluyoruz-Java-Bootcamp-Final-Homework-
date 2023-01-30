package com.biletx.request;


import com.biletx.client.model.CreditCard;


public class PaymentRequest {
    private CreditCard creditCard;
    private Integer userId;


    public PaymentRequest() {
    }

    public PaymentRequest(CreditCard creditCard, Integer userId) {
        this.creditCard = creditCard;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "creditCard=" + creditCard +
                ", userId=" + userId +
                '}';
    }
}
