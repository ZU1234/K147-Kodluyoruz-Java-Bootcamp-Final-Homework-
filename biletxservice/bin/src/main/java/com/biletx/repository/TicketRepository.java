package com.biletx.repository;

import com.biletx.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT SUM(price)totalPrices FROM tickets WHERE vehicle_id=?",nativeQuery =
            true)
    Long TotalPricesByVehicle(Integer vehicleId);
    @Query(value = "SELECT COUNT(*)totalTickets FROM tickets WHERE vehicle_id=?",nativeQuery =
            true)
    Long TotalTicketsByVehicle(Integer vehicleId);
}
