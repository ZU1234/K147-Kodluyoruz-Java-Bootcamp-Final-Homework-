package com.biletx.exception;

public class TicketDoesNotException extends RuntimeException{
    public TicketDoesNotException(String message) {
        super(message);
    }
}
