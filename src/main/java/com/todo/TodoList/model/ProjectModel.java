package com.todo.TodoList.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.Date;
import java.util.List;


public class ProjectModel {
    @DocumentId
    private String id;
    private String name;
    private Date creationDate;
    private Date updateDate;

    public ProjectModel() {
    }


    public ProjectModel(String id, String name, Date creationDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }



    public ProjectModel( String name, Date creationDate, Date updateDate) {
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
