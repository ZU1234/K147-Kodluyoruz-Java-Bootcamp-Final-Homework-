package com.biletx.response;

import org.springframework.http.HttpStatus;

public class PaymentResponse {
    private String message;
    private HttpStatus httpStatus;

    public PaymentResponse() {
      super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
