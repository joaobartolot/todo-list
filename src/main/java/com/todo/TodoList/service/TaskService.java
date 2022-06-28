package com.todo.TodoList.service;

import org.modelmapper.ModelMapper;
import com.todo.TodoList.dao.*;
import com.todo.TodoList.dto.*;
import com.todo.TodoList.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class TaskService {
    ModelMapper modelMapper;
    TaskDao dao;

    @Autowired
    public TaskService(ModelMapper modelMapper, TaskDao taskDao){
        this.modelMapper = modelMapper;
        this.dao = taskDao;

    }

    public TaskDTO findTaskById(String id) throws ExecutionException, InterruptedException {
        TaskModel task = this.dao.findById(id);
        TaskDTO taskDTO = this.modelMapper.map(task, TaskDTO.class);
        return taskDTO;
    }

    public ArrayList<TaskDTO> findTaskByProjectId(String id) throws ExecutionException, InterruptedException {
        ArrayList<TaskModel> tasks = this.dao.findByProjectId(id);
        ArrayList<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
        for (TaskModel taskModel : tasks) {
            taskDTOList.add(this.modelMapper.map(taskModel, TaskDTO.class));
        }
        return taskDTOList;
    }

    public TaskDTO createTask(TaskRequestDTO taskRequestDTO) throws ExecutionException, InterruptedException {

        TaskModel model = new TaskModel(
                taskRequestDTO.getTitle(),
                taskRequestDTO.getDescription(),
                false,
                new Date(),
                new Date(),
                taskRequestDTO.getOwner(),
                taskRequestDTO.getProjectId()
        );

        TaskModel task = this.dao.create(model);
        TaskDTO taskDTO = this.modelMapper.map(task, TaskDTO.class);
        System.out.println(taskDTO.getProjectId());
        return taskDTO;
    }

    public TaskDTO updateTask(String id, TaskRequestDTO taskRequestDTO) throws ExecutionException, InterruptedException {

        TaskModel taskFromBd = dao.findById(id);

        TaskModel taskModelToUpdate = taskFromBd.updateWith(
                taskRequestDTO.getTitle(),
                taskRequestDTO.getDescription(),
                taskRequestDTO.getDone(),
                taskRequestDTO.getOwner()
        );
        TaskModel task = this.dao.update(taskModelToUpdate);
        TaskDTO taskDTO = this.modelMapper.map(task, TaskDTO.class);

        return taskDTO;
    }

    public void deleteTask(String id) throws ExecutionException, InterruptedException {
        TaskModel taskFromBd = dao.findById(id);
        if(taskFromBd != null) {
            dao.delete(id);
        }
    }
    public void deleteTaskByProjectId(String id) throws ExecutionException, InterruptedException {
        dao.deleteByProjectId(id);
    }
}
