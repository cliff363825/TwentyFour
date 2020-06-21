package com.onevgo.springboot.zookeeper.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.onevgo.springboot.zookeeper.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Reference
    TicketService ticketService;

    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("UserService.hello: " + ticket);
    }
}
