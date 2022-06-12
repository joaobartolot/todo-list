package com.todo.TodoList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.todo.TodoList.service.*;
import com.todo.TodoList.dto.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService taskService){
        this.service = taskService;
    }

    @PostMapping("/")
    public ResponseEntity createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        service.createTask(taskRequestDTO);

        return ResponseEntity
                .status(202)
                .body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable int id) {
        TaskDTO taskDTO = service.findTaskById(id);

        if (taskDTO == null)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .ok(taskDTO);
    }
}
