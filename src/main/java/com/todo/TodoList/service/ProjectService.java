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
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {
    ModelMapper modelMapper;
    ProjectDao projectDao;
    TaskService taskService;

    @Autowired
    public ProjectService(ModelMapper modelMapper, ProjectDao projectDao, TaskService taskService) {
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
        ProjectModel model = new ProjectModel(
                projectRequestDTO.getName(),
                new Date(),
                new Date()
        );

        ProjectModel project = this.projectDao.create(model);
        ProjectDTO projectDTO = this.modelMapper.map(project, ProjectDTO.class);
        return projectDTO;
    }

    public ProjectDTO updateProject(String id, ProjectRequestDTO projectRequestDTO) throws ExecutionException, InterruptedException {

        ProjectModel project = projectDao.findById(id.trim());

        System.out.println(isNullOrEmpty(projectRequestDTO.getName()));
        if (!isNullOrEmpty(projectRequestDTO.getName())) {
            project.setName(projectRequestDTO.getName());
            project.setUpdateDate(new Date());

            project = this.projectDao.update(project);
        }

        ProjectDTO projectDTO = this.modelMapper.map(project, ProjectDTO.class);
        return projectDTO;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null  || string.isEmpty() || string.trim().isEmpty();
    }
}
