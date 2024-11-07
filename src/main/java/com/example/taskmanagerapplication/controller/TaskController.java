package com.example.taskmanagerapplication.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @GetMapping("/tasks")
    @ResponseBody
    public String getTasks() {



        return "ok";
    }
}
