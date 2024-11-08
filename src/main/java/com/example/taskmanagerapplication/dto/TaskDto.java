package com.example.taskmanagerapplication.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class TaskDto {

    Long id;
    String taskname;
    String username;
    String password;
    Date creatDate;
    Date updateDate;

    public TaskDto(Long id, String taskname, String username, String password, Date creatDate, Date updateDate) {
        this.id = id;
        this.taskname = taskname;
        this.username = username;
        this.password = password;
        this.creatDate = creatDate;
        this.updateDate = updateDate;
    }
}
