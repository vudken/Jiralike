package com.app.dao;

import com.app.model.Project;
import com.app.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveTicket(Ticket ticket) {
        final String SQL_INSERT_TICKET = "INSERT INTO ticket (summary, type, priority, status, project_id, assigned_to_user_id, description) " +
                                         "SELECT ?, ?::ticket_type, ?::ticket_priority, ?::ticket_status, ?, ?, ?";

        jdbcTemplate.update(SQL_INSERT_TICKET,
                ticket.getSummary(),
                ticket.getType(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getProjectId(),
                ticket.getAssignedToId(),
                ticket.getDescription()
        );
    }

    private Project mapProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setId(rs.getInt("id"));
        project.setName(rs.getString("name"));
        return project;
    }

    public List<Project> getProjects() {
        final String SQL_SELECT_PROJECTS = "SELECT * FROM project";
        RowMapper<Project> rowMapper = (rs, rowNumber) -> mapProject(rs);
        return jdbcTemplate.query(SQL_SELECT_PROJECTS, rowMapper);
    }

    private Ticket mapTicket(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setSummary(rs.getString("summary"));
        ticket.setType(rs.getString("type"));
        ticket.setPriority(rs.getString("priority"));
        ticket.setStatus(rs.getString("status"));
        ticket.setProjectId(rs.getInt("project_id"));
        ticket.setProjectName(rs.getString("project_name"));
        ticket.setDescription(rs.getString("description"));
        ticket.setAssignedToId(rs.getInt("assigned_to_user_id"));
        ticket.setReportedById(rs.getInt("reported_by_user_id"));
        ticket.setAssignedToUsername(rs.getString("assigned_to_username"));
        ticket.setReportedByUsername(rs.getString("reported_by_username"));
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        final String SQL_SELECT_ALL_TICKETS = "SELECT t.id, t.summary, type, priority, status, t.project_id, " +
                                                     "t.description, t.assigned_to_user_id, t.reported_by_user_id, " +
                                                     "p.name AS project_name, " +
                                                     "u1.username AS assigned_to_username, " +
                                                     "u2.username AS reported_by_username " +
                                              "FROM ticket t " +
                                              "LEFT JOIN project p ON t.project_id = p.id " +
                                              "LEFT JOIN users u1 ON t.assigned_to_user_id = u1.id " +
                                              "LEFT JOIN users u2 ON t.reported_by_user_id = u2.id;";

        RowMapper<Ticket> rowMapper = (rs, rowNumber) -> mapTicket(rs);
        return jdbcTemplate.query(SQL_SELECT_ALL_TICKETS, rowMapper);
    }
}
