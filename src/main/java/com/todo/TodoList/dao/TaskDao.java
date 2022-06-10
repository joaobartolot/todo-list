package com.todo.TodoList.dao;

import java.util.*;
import com.todo.TodoList.*;
import com.todo.TodoList.model.*;
import org.springframework.stereotype.Component;

@Component
public class TaskDao {

    ArrayList<TaskModel> database = new ArrayList<TaskModel>();
    public TaskDao(){
        TaskModel taskmodelDemo = new TaskModel(12,"testTitle","desc",false,new Date(), new Date(), "patrick");
        database.add(taskmodelDemo);
    }

    public TaskModel findOne(){
        TaskModel task = database.get(0);
        System.out.println(task);
        return task;
    }



}
