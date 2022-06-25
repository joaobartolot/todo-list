package com.todo.TodoList.service;

import com.todo.TodoList.dao.ProjectDao;
import com.todo.TodoList.dao.TaskDao;
import com.todo.TodoList.dto.ProjectDTO;
import com.todo.TodoList.dto.ProjectRequestDTO;
import com.todo.TodoList.dto.TaskDTO;
import com.todo.TodoList.dto.TaskRequestDTO;
import com.todo.TodoList.model.ProjectModel;
import com.todo.TodoList.model.TaskModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {
    ModelMapper modelMapper;
    ProjectDao projectDao;
    TaskService taskService;

    @Autowired
    public ProjectService(ModelMapper modelMapper, ProjectDao projectDao, TaskService taskService){
        this.modelMapper = modelMapper;
         this.projectDao = projectDao;
         this.taskService = taskService;
    }

    public ProjectDTO findProjectById(String id) throws ExecutionException, InterruptedException {
        ProjectModel project = this.projectDao.findById(id);
        ArrayList<TaskDTO> taskList = taskService.findTaskByProjectId(project.getId());
        ProjectDTO projectDTO = this.modelMapper.map(project, ProjectDTO.class);
        projectDTO.setTasks(taskList);
        return projectDTO;
    }

    public ProjectDTO createProject(ProjectRequestDTO projectRequestDTO) throws ExecutionException, InterruptedException {
        ProjectModel project = this.projectDao.create(projectRequestDTO.getName());
        ProjectDTO projectDTO = this.modelMapper.map(project, ProjectDTO.class);
        return projectDTO;
    }

}
