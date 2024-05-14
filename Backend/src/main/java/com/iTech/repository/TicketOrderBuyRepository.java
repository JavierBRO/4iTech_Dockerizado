package com.iTech.repository;

import com.iTech.models.TicketOrderBuy;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TicketOrderBuyRepository extends JpaRepository<TicketOrderBuy, Long> {
    boolean existsByUser_IdAndTicket_Id(Long userId, Long ticketId);

    //List<TicketOrderBuy> findByTicketOrderBuy_IdOrderByIdDesc(Long id);

}