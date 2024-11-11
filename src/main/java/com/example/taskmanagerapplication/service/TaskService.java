package com.example.taskmanagerapplication.service;

import com.example.taskmanagerapplication.dto.*;
import com.example.taskmanagerapplication.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    List<TaskDto> findTasks(String userName, LocalDateTime updateAt);

    void addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto findTaskById(Long taskId);

    TaskResponseDto updateTaskById(Long taskId, TaskRequestDto taskRequestDto);

    void deleteTaskById(Long taskId, String password);
}
