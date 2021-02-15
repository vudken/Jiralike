package com.app.service;

import com.app.dao.TicketDAO;
import com.app.model.Project;
import com.app.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketDAO ticketDAO;

    public void saveTicket(Ticket ticket) {
        ticketDAO.saveTicket(ticket);
    }

    public List<Project> getProjects() {
        return ticketDAO.getProjects();
    }
}
