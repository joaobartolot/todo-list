package com.todo.TodoList.dto;

public class TaskRequestDTO {
    private String title;
    private String description;
    private String owner;
    private Boolean done;
    private String projectId;

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public String getOwner() {
        return owner;
    }

    public Boolean getDone() {
        return done;
    }

    public String getProjectId() {
        return projectId;
    }
}
