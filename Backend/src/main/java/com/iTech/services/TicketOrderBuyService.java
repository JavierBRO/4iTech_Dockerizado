package com.iTech.services;

import com.iTech.models.TicketOrderBuy;

import java.util.List;


public interface TicketOrderBuyService {
    List<TicketOrderBuy> findTicketOrderBuy();

    TicketOrderBuy findById(Long id);

    TicketOrderBuy createTicketOrderBuy(TicketOrderBuy ticketOrderBuy);

    TicketOrderBuy updateTicketOrderBuy(Long id, TicketOrderBuy ticketOrderBuy);

    boolean deleteTicketOrderBuy(Long id);
}






