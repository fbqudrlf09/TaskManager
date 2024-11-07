package com.example.taskmanagerapplication.entity;

import java.util.Date;

public class Task {

    Long id;
    String taskname;
    String username;
    Date date;

    public Task(String taskname, String username, Date date) {
        this.taskname = taskname;
        this.username = username;
        this.date = date;
    }
}
