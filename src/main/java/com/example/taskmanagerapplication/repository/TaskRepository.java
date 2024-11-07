package com.example.taskmanagerapplication.repository;

import com.example.taskmanagerapplication.dto.TaskDto;

import java.util.List;

public interface TaskRepository {

    TaskDto saveTask(TaskDto task);

    TaskDto findTaskById(Long id);

    int upDateTask(Long id, TaskDto task);

    int deleteTask(Long id);

    List<TaskDto> findAllTaskFilterByUpdateAtAndUserName(String updateAt, String userName);
}
