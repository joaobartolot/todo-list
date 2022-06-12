package com.todo.TodoList.service;

import com.todo.TodoList.dao.TaskDao;
import com.todo.TodoList.dto.TaskRequestDTO;
import com.todo.TodoList.model.TaskModel;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskDao dao;

    @Test
    public void shouldCreateATask() {
        final TaskRequestDTO taskRequestDTO = Mockito.mock(TaskRequestDTO.class);

        service.createTask(taskRequestDTO);

        final TaskModel taskModel = dao.findById(dao.getMaxId());

        Assertions.assertNotNull(taskModel);
        Assertions.assertEquals(taskRequestDTO.getTitle(), taskModel.getTitle());
        Assertions.assertEquals(taskRequestDTO.getDescription(), taskModel.getDescription());
        Assertions.assertEquals(taskRequestDTO.getOwner(), taskModel.getOwner() );
    }
}
