package com.app.service;

import com.app.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean validateTicket(Ticket ticket) {
        return  ticket.getSummary() != null &&
                ticket.getSummary().length() >= 3 &&
                !ticket.getType().isEmpty() &&
                !ticket.getPriority().isEmpty() &&
                !ticket.getStatus().isEmpty() &&
                ticket.getProjectId() > 0;
    }
}
