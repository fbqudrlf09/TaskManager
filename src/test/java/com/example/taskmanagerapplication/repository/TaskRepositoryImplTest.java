package com.example.taskmanagerapplication.repository;

import com.example.taskmanagerapplication.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Component
class TaskRepositoryImplTest {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryImplTest(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void findTasks() {
        List<Task> tasks = jdbcTemplate.query("SELECT * FROM task", taskRowMapper());
    }

    private RowMapper<Task> taskRowMapper() {
        return new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Task(
                        rs.getLong("id"),
                        rs.getString("task_name"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getDate("create_at"),
                        rs.getDate("update_at")
                );
            }
        };
    }


}