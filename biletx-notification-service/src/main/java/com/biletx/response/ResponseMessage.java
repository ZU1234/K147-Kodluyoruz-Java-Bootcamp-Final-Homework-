package com.biletx.response;

import com.biletx.model.Ticket;

public class ResponseMessage {
    private String message;
    private Ticket ticket;

   private String ticketMessage ="Bilet Bilgileri : " +
            "\nBilet Id : " + ticket.getId() +
            "\nAraç tipi : " + ticket.getVehicle().getVehicleType() +
            "\nAd-Soyad : " + ticket.getPassengerName() +
            "\nCinsiyet : " + ticket.getGender() +
            "\nGüzergah : " + ticket.getVehicle().getFromWhere() +
            "-" + ticket.getVehicle().getWhereTo() +
            "\nSaat : " + ticket.getVehicle().getDepartureClock();


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getTicketMessage() {
        return ticketMessage;
    }

    public void setTicketMessage(String ticketMessage) {
        this.ticketMessage = ticketMessage;
    }
}
