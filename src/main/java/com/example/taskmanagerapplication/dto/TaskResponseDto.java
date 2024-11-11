package com.example.taskmanagerapplication.dto;

import com.example.taskmanagerapplication.entity.Task;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class TaskResponseDto {

    Long id;
    String taskName;
    String username;
    LocalDateTime creatDate;
    LocalDateTime updateDate;

    public TaskResponseDto(Long id, String taskName, String username, LocalDateTime creatDate, LocalDateTime updateDate) {
        this.id = id;
        this.taskName = taskName;
        this.username = username;
        this.creatDate = creatDate;
        this.updateDate = updateDate;
    }

    public TaskResponseDto(Task task) {
        this.id = task.getId();
        this.taskName = task.getTaskname();
        this.username = task.getUsername();
        this.creatDate = task.getCreatDate();
        this.updateDate = task.getUpdateDate();
    }
}
