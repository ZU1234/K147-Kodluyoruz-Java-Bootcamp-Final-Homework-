package com.biletx.repository;

import com.biletx.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
   // List<Ticket> findAllByUserId(Integer userId);
}
