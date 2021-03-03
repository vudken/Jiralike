package com.app.controller;

import com.app.model.Ticket;
import com.app.service.TicketService;
import com.app.service.UserService;
import com.app.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping("/createTicket")
    public String getTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("projects", ticketService.getProjects());
        model.addAttribute("users", userService.getAllUsers());
        return "createTicket.html";
    }

    @PostMapping("/createTicket")
    public String createTicket(@ModelAttribute Ticket ticket, Model model) {
        if (!validationService.validateTicket(ticket)) {
            return "createTicket.html";
        }
        model.addAttribute("ticket", ticket);
        ticketService.saveTicket(ticket);
        return "response.html";
    }

    @GetMapping("/allTickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "allTickets.html";
    }
}
