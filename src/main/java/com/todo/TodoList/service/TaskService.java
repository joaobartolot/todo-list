package com.todo.TodoList.service;

import java.util.Date;
import org.modelmapper.ModelMapper;
import com.todo.TodoList.dao.*;
import com.todo.TodoList.dto.*;
import com.todo.TodoList.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskService {
    ModelMapper modelMapper;
    TaskDao taskDao;

    @Autowired
    public TaskService(ModelMapper modelMapper,TaskDao taskDao){
        this.modelMapper = modelMapper;
         this.taskDao = taskDao;

    }

    public TaskDTO findOneTask(){
        TaskModel task = this.taskDao.findOne();
        TaskDTO taskDTO = this.modelMapper.map(task, TaskDTO.class);
        return taskDTO;
    }

}
