package com.todo.TodoList.dao;

import java.util.*;

import com.todo.TodoList.model.*;
import org.springframework.stereotype.Component;

@Component
public class TaskDao {

    public ArrayList<TaskModel> database = new ArrayList<TaskModel>();

    public TaskModel findById(int id){
        return database
                .stream()
                .filter(taskModel -> taskModel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void create(String title, String description, String owner) {
        TaskModel model = new TaskModel(
                this.getMaxId() + 1,
                title,
                description,
                false,
                new Date(),
                new Date(),
                owner
        );

        database.add(model);
    }

    public int getMaxId() {
        if (database.isEmpty())
            return 0;

        TaskModel task = database.stream().max(Comparator.comparing(TaskModel::getId)).get();

        return task.getId();
    }
}
