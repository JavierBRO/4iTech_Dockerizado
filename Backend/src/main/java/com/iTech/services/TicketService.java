package com.iTech.services;


import com.iTech.models.Ticket;

import java.util.List;


public interface TicketService {

    List<Ticket> findTicket();
    Ticket findById(Long id);


    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Long id, Ticket ticket);

    boolean deleteTicket(Long id);
}

