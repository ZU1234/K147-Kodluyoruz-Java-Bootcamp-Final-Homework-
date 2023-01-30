package com.biletx.model;

import com.biletx.enums.CreditCardType;

import java.time.Month;
import java.time.Year;

public class CreditCard {
    private String creditCardNumber;
    private CreditCardType type;
    private String cvv;
    private String cardHolderName;
    private int cardExpityYear;
    private Month cardExpityMonth;

    public CreditCard(String creditCardNumber, CreditCardType type, String cvv, String cardHolderName,
                      int cardExpityYear, Month cardExpityMonth) {
        super();
        this.creditCardNumber = creditCardNumber;
        this.type = type;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
        this.cardExpityYear = cardExpityYear;
        this.cardExpityMonth = cardExpityMonth;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getCardExpityYear() {
        return cardExpityYear;
    }

    public void setCardExpityYear(int cardExpityYear) {
        this.cardExpityYear = cardExpityYear;
    }

    public Month getCardExpityMonth() {
        return cardExpityMonth;
    }

    public void setCardExpityMonth(Month cardExpityMonth) {
        this.cardExpityMonth = cardExpityMonth;
    }
}
