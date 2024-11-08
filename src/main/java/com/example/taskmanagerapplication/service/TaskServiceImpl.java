package com.example.taskmanagerapplication.service;

import com.example.taskmanagerapplication.dto.TaskDto;
import com.example.taskmanagerapplication.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> findTasks(String updateAt, String userName) {
        return taskRepository.findAllTaskFilterByUpdateAtAndUserName(updateAt, userName);
    }

    @Override
    public void addTask(TaskDto taskDto) {
        taskRepository.saveTask(taskDto);
    }

    @Override
    public TaskDto findTaskById(Long taskId) {
        return taskRepository.findTaskById(taskId);
    }

    @Transactional
    @Override
    public TaskDto updateTaskById(Long taskId, TaskDto taskDto) {
        int updateRow = taskRepository.upDateTask(taskId, taskDto);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return taskRepository.findTaskById(taskId);
    }

    @Transactional
    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteTask(taskId);
    }
}
