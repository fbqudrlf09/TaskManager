package com.example.taskmanagerapplication.service;

import com.example.taskmanagerapplication.dto.TaskDto;
import com.example.taskmanagerapplication.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> findTasks(String updateAt, String username);

    void addTask(TaskDto taskDto);

    TaskDto findTaskById(Long taskId);

    TaskDto updateTaskById(Long taskId, TaskDto taskDto);

    void deleteTaskById(Long taskId);
}
