package com.example.taskmanagerapplication.repository;

import com.example.taskmanagerapplication.dto.TaskDto;
import com.example.taskmanagerapplication.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TaskRepositoryImpl implements TaskRepository{

    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TaskDto saveTask(TaskDto task) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("id");

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("task_name", task.getTaskname());
        parameter.put("user_name", task.getUsername());
        parameter.put("password", task.getPassword());
        parameter.put("create_at", LocalDateTime.now());
        parameter.put("update_at", LocalDateTime.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));

        return task;
    }

    @Override
    public TaskDto findTaskById(Long id) {
        List<TaskDto> tasks = jdbcTemplate.query("SELECT * FROM task WHERE id = ?", taskRowMapper2(), id);

        return tasks.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @Override
    public int upDateTask(Long id, TaskDto task) {
        return jdbcTemplate.update(
                "UPDATE task SET task_name = ?, user_name = ?, update_at = ? WHERE id = ?;",
                task.getTaskname(), task.getUsername(), LocalDateTime.now() ,id);
    }

    @Override
    public int deleteTask(Long id) {
         return jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }

    @Override
    public List<TaskDto> findAllTaskFilterByUpdateAtAndUserName(String updateAt, String userName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM task WHERE 1 = 1");
        if (updateAt != null) {
            sb.append(" AND update_at = '").append(updateAt).append("'");
        }
        if (userName != null) {
            sb.append(" AND user_name = '").append(userName).append("'");
        }

        sb.append(" ORDER BY update_at DESC;");

        return jdbcTemplate.query(sb.toString(), taskRowMapper());
    }

    private RowMapper<TaskDto> taskRowMapper() {
        return new RowMapper<TaskDto>() {
            @Override
            public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TaskDto(
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

    private RowMapper<TaskDto> taskRowMapper2() {
        return new RowMapper<TaskDto>() {
            @Override
            public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TaskDto(
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
