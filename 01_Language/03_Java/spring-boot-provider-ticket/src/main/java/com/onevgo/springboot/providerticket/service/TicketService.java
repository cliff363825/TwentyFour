package com.onevgo.springboot.providerticket.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public String getTicket() {
        System.out.println("TicketService.getTicket: 8001");
        return "《厉害了，我的国》";
    }
}
