package com.todo.TodoList.dto;

import java.util.Date;

public class TaskDTO {
    private String id;
    private String title;
    private String description;
    private boolean done;
    private Date creationDate;
    private Date updateDate;
    private String owner;

    public TaskDTO(){

    }

    public TaskDTO(String id, String title, String description, boolean done, Date creationDate, Date updateDate, String owner) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
