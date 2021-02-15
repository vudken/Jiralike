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

        final String SQL_INSERT_TICKET = " INSERT INTO ticket (summary, type_id, priority_id, status_id, project_id, description)" +
                                         " SELECT ?, t.id, p.id, s.id, ?, ?" +
                                         " FROM type as t, priority as p, status as s" +
                                         " WHERE t.name=? AND p.name=? AND s.name=?";

        jdbcTemplate.update(SQL_INSERT_TICKET,
                ticket.getSummary(),
                ticket.getProjectId(),
                ticket.getDescription(),
                ticket.getType(),
                ticket.getPriority(),
                ticket.getStatus()
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
}
