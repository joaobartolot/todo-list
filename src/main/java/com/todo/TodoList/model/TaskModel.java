package com.todo.TodoList.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.Date;

public class TaskModel {
    @DocumentId
    private String id;
    private String title;
    private String description;
    private boolean done;
    private Date creationDate;
    private Date updateDate;
    private String owner;
    private String projectId;

    public TaskModel() {
    }

    public TaskModel(String id, String title, String description, boolean done, Date creationDate, Date updateDate, String owner, String projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.owner = owner;
        this.projectId = projectId;
    }

    public TaskModel(String title, String description, boolean done, Date creationDate, Date updateDate, String owner, String projectId) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.owner = owner;
        this.projectId = projectId;
    }

    public TaskModel updateWith(String title, String description, Boolean done, String owner) {
        return new TaskModel(
                this.id,
                title!= null && title.length() > 0 ?  title : this.title,
                description != null && description.length() > 0 ? description : this.description,
                done != null ? done : this.done,
                this.getCreationDate(),
                new Date(),
                owner != null && owner.length() > 0 ? owner : this.owner,
                this.projectId
        );
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

    public String getProjectId() { return projectId;  }

    public void setProjectId(String projectId) { this.projectId = projectId;  }
}
