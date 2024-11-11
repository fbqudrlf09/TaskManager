package com.example.taskmanagerapplication.repository;

import com.example.taskmanagerapplication.entity.Task;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveTask(String taskName, String userName, String password) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("id");

        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("task_name", taskName);
        parameter.put("user_name", userName);
        parameter.put("password", password);
        parameter.put("create_at", now);
        parameter.put("update_at", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        List<Task> tasks = jdbcTemplate.query("SELECT * FROM task WHERE id = ?", taskRowMapper(), id);

        return tasks.stream().findAny();
    }


    @Override
    public int upDateTask(Long id, String taskName, String userName, String password) {

        return jdbcTemplate.update(
                "UPDATE task SET task_name = ?, user_name = ?, update_at = ? WHERE id = ? AND password = ?;",
                taskName, userName, LocalDateTime.now(), id, password);
    }

    @Override
    public int deleteTask(Long taskId, String password) {
        return jdbcTemplate.update("DELETE FROM task WHERE id = ? AND password = ?", taskId, password);
    }

    @Override
    public List<Task> findAllTaskFilterByUpdateAtAndUserName(String username, LocalDateTime updateAt) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM task WHERE 1 = 1");

      //  List<Object> params = new ArrayList<>();

        if (updateAt != null) {
            sb.append(" AND update_at = '").append(updateAt).append("'");
           // params.add(updateAt);
        }
        if (username!= null) {
            sb.append(" AND user_name = '").append(username).append("'");
          //  params.add(username);
        }

        sb.append(" ORDER BY update_at DESC;");

      //  return jdbcTemplate.query(sb.toString(), params.toArray(),taskRowMapper());


        List<Task> tasks = jdbcTemplate.query(sb.toString(), taskRowMapper());
        return tasks;
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
                        rs.getObject("create_at", LocalDateTime.class),
                        rs.getObject("update_at", LocalDateTime.class)
                );
            }
        };
    }
}