package com.todo.TodoList.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.todo.TodoList.service.*;
import com.todo.TodoList.dto.*;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public TaskDTO task() {
        return taskService.findOneTask();
    }
}
