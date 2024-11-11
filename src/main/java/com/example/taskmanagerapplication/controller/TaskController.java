package com.example.taskmanagerapplication.controller;


import com.example.taskmanagerapplication.dto.*;
import com.example.taskmanagerapplication.entity.Task;
import com.example.taskmanagerapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> findTasks(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") LocalDateTime updateAt
            ) {

        // dto로 변환
        return  taskService.findTasks(username, updateAt);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody TaskRequestDto taskRequestDto) {
        taskService.addTask(taskRequestDto);
    }

    @GetMapping("/{taskId}")
    public TaskResponseDto findTaskById(@PathVariable Long taskId) {
        return taskService.findTaskById(taskId);
    }

    @PostMapping("/{taskId}")
    public TaskResponseDto updateTaskById(@PathVariable Long taskId,
                                                         @RequestBody TaskRequestDto taskRequestDto) {
        return taskService.updateTaskById(taskId, taskRequestDto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable Long taskId, @RequestBody String password) {
        taskService.deleteTaskById(taskId, password);
    }
}