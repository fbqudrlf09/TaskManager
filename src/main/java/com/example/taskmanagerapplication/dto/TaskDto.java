package com.example.taskmanagerapplication.dto;

import com.example.taskmanagerapplication.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class TaskDto {
    Long id;
    String taskname;
    String username;
    LocalDateTime creatDate;
    LocalDateTime updateDate;

    public TaskDto(Long id, String taskName, String userName, LocalDateTime creatDate, LocalDateTime updateDate) {
        this.id = id;
        this.taskname = taskName;
        this.username = userName;
        this.creatDate = creatDate;
        this.updateDate = updateDate;
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.taskname = task.getTaskname();
        this.username = task.getUsername();
        this.creatDate = task.getCreatDate();
        this.updateDate = task.getUpdateDate();
    }
}
