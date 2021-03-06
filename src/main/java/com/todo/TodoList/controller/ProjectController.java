package com.todo.TodoList.controller;

import com.todo.TodoList.dto.ProjectDTO;
import com.todo.TodoList.dto.ProjectRequestDTO;
import com.todo.TodoList.dto.TaskDTO;
import com.todo.TodoList.dto.TaskRequestDTO;
import com.todo.TodoList.service.ProjectService;
import com.todo.TodoList.service.TaskService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(responseCode = "201", description = "Project created successfully"),
            @ApiResponse(responseCode = "400", description = "Body was sent null",
                    content = {@Content(
                            schema = @Schema()
                    )})
    })
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequestDTO projectRequestDTO) throws ExecutionException, InterruptedException {
        if (projectRequestDTO == null)
            return ResponseEntity
                    .status(400)
                    .body(null);

        ProjectDTO project = service.createProject(projectRequestDTO);

        return ResponseEntity
                .status(201)
                .body(project);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found"),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = {@Content(
                            schema = @Schema()
                    )})
    })
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String id) throws ExecutionException, InterruptedException {
        ProjectDTO project = service.findProjectById(id);

        if (project == null)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .ok(project);
    }


    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated successfully"),
            @ApiResponse(responseCode = "400", description = "Body was sent null",
                    content = {@Content(
                            schema = @Schema()
                    )})
    })
    public ResponseEntity<ProjectDTO> updateTask(@PathVariable(value = "id") String id, @RequestBody ProjectRequestDTO projectRequestDTO) throws ExecutionException, InterruptedException {

        if (projectRequestDTO == null)
            return ResponseEntity
                    .status(400)
                    .body(null);

        ProjectDTO projectDTO =  service.updateProject(id, projectRequestDTO);

        return ResponseEntity
                .status(200)
                .body(projectDTO);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted successfully",
                    content = {@Content(
                            schema = @Schema()
                    )})
    })
    public ResponseEntity<Void> deleteTask(@PathVariable(value = "id") String id) throws ExecutionException, InterruptedException {
        service.deleteProject(id);

        return ResponseEntity
                .status(204)
                .body(null);
    }
}
