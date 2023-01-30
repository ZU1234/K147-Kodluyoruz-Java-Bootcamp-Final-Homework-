package com.biletx.response;

public class TotalTicketsAndTotalPricesByVehicleResponse {
    private Long totalTickets;
    private Long tolalPrices;

    public TotalTicketsAndTotalPricesByVehicleResponse() {
        super();
    }

    public TotalTicketsAndTotalPricesByVehicleResponse(Long totalTickets, Long tolalPrices) {
        this.totalTickets = totalTickets;
        this.tolalPrices = tolalPrices;
    }

    public Long getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Long totalTickets) {
        this.totalTickets = totalTickets;
    }

    public Long getTolalPrices() {
        return tolalPrices;
    }

    public void setTolalPrices(Long tolalPrices) {
        this.tolalPrices = tolalPrices;
    }

    @Override
    public String toString() {
        return "TotalTicketsAndTotalPricesByVehicleResponse{" +
                "totalTickets=" + totalTickets +
                ", tolalPrices=" + tolalPrices +
                '}';
    }
}
