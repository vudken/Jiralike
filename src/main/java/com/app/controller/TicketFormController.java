package com.app.controller;

import com.app.model.Ticket;
import com.app.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketFormController {

    @Autowired
    private ValidationService validationService;

    @GetMapping("/ticketForm")
    public String getTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticketForm.html";
    }

    @PostMapping("/ticketForm")
    public String createTicket(@ModelAttribute Ticket ticket, Model model) {
        if(!validationService.validateTicket(ticket)) {
            return "ticketForm.html";
        }
        model.addAttribute("ticket", ticket);
        return "response.html";
    }
}
