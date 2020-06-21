package com.onevgo.springboot.providerticket.controller;

import com.onevgo.springboot.providerticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    private String getTicket() {
        return ticketService.getTicket();
    }
}
