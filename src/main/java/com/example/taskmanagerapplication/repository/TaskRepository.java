package com.example.taskmanagerapplication.repository;

import com.example.taskmanagerapplication.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void saveTask(String taskName, String userName, String password);

    Optional<Task> findTaskById(Long id);

    int upDateTask(Long id, String taskName, String userName, String password);

    int deleteTask(Long id, String password);

    List<Task> findAllTaskFilterByUpdateAtAndUserName(String userName, LocalDateTime updateAt);
}
