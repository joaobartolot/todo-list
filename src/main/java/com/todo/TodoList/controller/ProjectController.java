package com.todo.TodoList.controller;

import com.todo.TodoList.dto.ProjectDTO;
import com.todo.TodoList.dto.ProjectRequestDTO;
import com.todo.TodoList.dto.TaskDTO;
import com.todo.TodoList.dto.TaskRequestDTO;
import com.todo.TodoList.service.ProjectService;
import com.todo.TodoList.service.TaskService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.service = projectService;
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Body was sent null")
    })
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequestDTO projectRequestDTO) throws ExecutionException, InterruptedException {
        if (projectRequestDTO == null)
            return ResponseEntity
                    .status(400)
                    .body(null);

        ProjectDTO project = service.createProject(projectRequestDTO);

        return ResponseEntity
                .status(202)
                .body(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String id) throws ExecutionException, InterruptedException {
        ProjectDTO project = service.findProjectById(id);

        if (project == null)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .ok(project);
    }


}
