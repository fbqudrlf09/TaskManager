package com.example.taskmanagerapplication.dto;

import lombok.Getter;

@Getter
public class TaskRequestDto {

    String taskname;
    String username;
    String password;

    public TaskRequestDto(String taskname, String username, String password) {
        this.taskname = taskname;
        this.username = username;
        this.password = password;
    }
}
