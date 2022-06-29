package com.todo.TodoList.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.todo.TodoList.service.*;
import com.todo.TodoList.dto.*;

import java.util.concurrent.ExecutionException;

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
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Body was sent null")
    })
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) throws ExecutionException, InterruptedException {
        if (taskRequestDTO == null)
            return ResponseEntity
                    .status(400)
                    .body(null);

        TaskDTO task = service.createTask(taskRequestDTO);

        return ResponseEntity
                .status(201)
                .body(task);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskDTO> getTask(@PathVariable String id) throws ExecutionException, InterruptedException {
            TaskDTO taskDTO = service.findTaskById(id);

            if (taskDTO == null)
                return ResponseEntity
                        .notFound()
                        .build();
            return ResponseEntity
                    .ok(taskDTO);
    }
    
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Body was sent null")
    })
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(value = "id") String id, @RequestBody TaskRequestDTO taskRequestDTO) throws ExecutionException, InterruptedException {

        if (taskRequestDTO == null)
            return ResponseEntity
                    .status(400)
                    .body(null);
        TaskDTO taskDTO =  service.updateTask(id,taskRequestDTO);
        return ResponseEntity
                .status(204)
                .body(null);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully")
    })
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable(value = "id") String id) throws ExecutionException, InterruptedException {
        service.deleteTask(id);

        return ResponseEntity
                .status(204)
                .body(null);
    }

}
