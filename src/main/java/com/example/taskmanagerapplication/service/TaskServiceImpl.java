package com.example.taskmanagerapplication.service;

import com.example.taskmanagerapplication.dto.*;
import com.example.taskmanagerapplication.entity.Task;
import com.example.taskmanagerapplication.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> findTasks(String userName, LocalDateTime updateAt) {
        List<Task> tasks = taskRepository.findAllTaskFilterByUpdateAtAndUserName(userName, updateAt);


        return tasks.stream().map(TaskDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskRequestDto taskRequestDto) {
        taskRepository.saveTask(taskRequestDto.getTaskname(), taskRequestDto.getUsername(), taskRequestDto.getPassword());
    }

    @Override
    public TaskResponseDto findTaskById(Long taskId) {
        Optional<Task> taskById = taskRepository.findTaskById(taskId);
        Task task = taskById.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new TaskResponseDto(task);
    }

    @Transactional
    @Override
    public TaskResponseDto updateTaskById(Long taskId, TaskRequestDto taskRequestDto) {
        int updateRow = taskRepository.upDateTask(taskId, taskRequestDto.getTaskname(), taskRequestDto.getUsername(), taskRequestDto.getPassword());

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, taskId + ": 아이디에 맞는 데이터가 없거나 비밀번호가 틀렸습니다");
        }

        return findTaskById(taskId);
    }

    @Transactional
    @Override
    public void deleteTaskById(Long taskId, String password) {
        int deletedRow = taskRepository.deleteTask(taskId, password);
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, taskId + ": 아이디에 맞는 데이터가 없거나 비밀번호가 틀렸습니다");
        }
    }
}
