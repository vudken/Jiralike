package com.app.dao;

import com.app.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveTicket(Ticket ticket) {

        final String SQL_INSERT_TICKET = " INSERT INTO ticket (summary, type_id, priority_id, status_id, description)" +
                                         " SELECT ?, t.id, p.id, s.id, ?" +
                                         " FROM type as t, priority as p, status as s" +
                                         " WHERE t.name=? AND p.name=? AND s.name=?";

        jdbcTemplate.update(SQL_INSERT_TICKET,
                ticket.getSummary(),
                ticket.getDescription(),
                ticket.getType(),
                ticket.getPriority(),
                ticket.getStatus()
        );
    }
}
