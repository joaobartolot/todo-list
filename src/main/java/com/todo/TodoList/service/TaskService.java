package com.todo.TodoList.service;

import org.modelmapper.ModelMapper;
import com.todo.TodoList.dao.*;
import com.todo.TodoList.dto.*;
import com.todo.TodoList.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service
public class TaskService {
    ModelMapper modelMapper;
    TaskDao taskDao;

    @Autowired
    public TaskService(ModelMapper modelMapper,TaskDao taskDao){
        this.modelMapper = modelMapper;
         this.taskDao = taskDao;

    }

    public TaskDTO findTaskById(int id){
        TaskModel task = this.taskDao.findById(id);
        TaskDTO taskDTO = this.modelMapper.map(task, TaskDTO.class);
        return taskDTO;
    }

    public void createTask(TaskRequestDTO taskRequestDTO) {
        this.taskDao.create(taskRequestDTO.getTitle(), taskRequestDTO.getDescription(), taskRequestDTO.getOwner());
    }
}
