package com.todo.TodoList.dao;

import com.todo.TodoList.dao.TaskDao;
import com.todo.TodoList.dto.TaskDTO;
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
public class TaskDaoTest {

    @Autowired
    private TaskDao dao;

    @Test
    public void shouldCreateAddATask() {
        final String title = "Some title";
        final String description = "Some description";
        final String owner = "Some owner";

        dao.create(title, description, owner);

        final TaskModel taskModel = dao.findById(dao.getMaxId());

        Assertions.assertNotNull(taskModel);
        Assertions.assertEquals(title, taskModel.getTitle());
        Assertions.assertEquals(description, taskModel.getDescription());
        Assertions.assertEquals(owner, taskModel.getOwner() );
    }
}
