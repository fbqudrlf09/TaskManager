package com.example.taskmanagerapplication.controller;


import com.example.taskmanagerapplication.dto.TaskDto;
import com.example.taskmanagerapplication.entity.Task;
import com.example.taskmanagerapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> findTasks(@RequestParam(required = false) String update_at,
                                   @RequestParam(required = false) String username) {
        return taskService.findTasks(update_at, username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addTask(@RequestBody TaskDto taskDto) {
        taskService.addTask(taskDto);
    }

    @GetMapping("/{taskId}")
    public TaskDto findTaskById(@PathVariable Long taskId) {
        return taskService.findTaskById(taskId);
    }

    @PostMapping("/{taskId}")
    public TaskDto updateTaskById(@PathVariable Long taskId,
                                  @RequestBody TaskDto taskDto
                                ) {
        return taskService.updateTaskById(taskId, taskDto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable Long taskId, String password) {
        taskService.deleteTaskById(taskId);
    }
}
