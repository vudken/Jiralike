package com.app.controller;

import com.app.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketFormController {

    @GetMapping("/ticketForm")
    public String getTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket-form.html";
    }

    @PostMapping("/ticketForm")
    public String createTicket(@ModelAttribute Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        return "test.html";
    }
}
