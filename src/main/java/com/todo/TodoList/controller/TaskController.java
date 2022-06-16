package com.todo.TodoList.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Body was sent null")
    })
    public ResponseEntity createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        if (taskRequestDTO == null)
            return ResponseEntity
                    .status(400)
                    .body(null);

        service.createTask(taskRequestDTO);

        return ResponseEntity
                .status(202)
                .body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable String id) {
        TaskDTO taskDTO = service.findTaskById(id);

        if (taskDTO == null)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .ok(taskDTO);
    }
}
