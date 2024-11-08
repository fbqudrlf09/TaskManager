package com.example.taskmanagerapplication.entity;

import lombok.Getter;

import java.util.Date;

@Getter
public class Task {

    Long id;
    String taskname;
    String username;
    String password;
    String creatDate;
    String updateDate;

    public Task(Long id, String taskname, String username, String password, String creatDate, String updateDate) {
        this.id = id;
        this.taskname = taskname;
        this.username = username;
        this.password = password;
        this.creatDate = creatDate;
        this.updateDate = updateDate;
    }
}
