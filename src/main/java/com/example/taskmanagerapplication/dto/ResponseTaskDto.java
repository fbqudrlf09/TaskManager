package com.example.taskmanagerapplication.dto;

import java.util.Date;

public class ResponseTaskDto {
    String taskname;
    String username;
    Date date;

    public ResponseTaskDto(String taskname, String username, Date date) {
        this.taskname = taskname;
        this.username = username;
        this.date = date;
    }
}
