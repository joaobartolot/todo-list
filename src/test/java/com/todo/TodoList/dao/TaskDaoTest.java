package com.todo.TodoList.dao;

import com.google.cloud.firestore.DocumentReference;
import com.todo.TodoList.dao.TaskDao;
import com.todo.TodoList.dto.TaskDTO;
import com.todo.TodoList.model.TaskModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDaoTest {

    @Autowired
    private TaskDao dao;

    @Test
    public void shouldCreateTask() throws ExecutionException, InterruptedException {
        final String title = "Some title";
        final String description = "Some description";
        final String owner = "Some owner";

        TaskModel taskModel = dao.create(title, description, owner);

        Assertions.assertNotNull(taskModel);
        Assertions.assertNotNull(taskModel.getId());
        Assertions.assertEquals(title, taskModel.getTitle());
        Assertions.assertEquals(description, taskModel.getDescription());
        Assertions.assertEquals(owner, taskModel.getOwner() );
    }

    @Test
    public void shouldCreateAddATask() throws ExecutionException, InterruptedException {
        final String title = "Some title";
        final String description = "Some description";
        final String owner = "Some owner";

        final TaskModel expected = dao.create(title, description, owner);

        TaskModel taskModel = dao.findById(expected.getId());

        Assertions.assertNotNull(taskModel);
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expected, taskModel));
    }
}
