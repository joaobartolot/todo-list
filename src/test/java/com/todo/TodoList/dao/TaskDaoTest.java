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

import java.util.Date;
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
        final String projectId = "Some projectId";

        final TaskModel model = new TaskModel(
                title,
                description,
                false,
                new Date(),
                new Date(),
                owner,
                projectId
        );


        TaskModel taskModel = dao.create(model);

        Assertions.assertNotNull(taskModel);
        Assertions.assertNotNull(taskModel.getId());
        Assertions.assertEquals(title, taskModel.getTitle());
        Assertions.assertEquals(description, taskModel.getDescription());
        Assertions.assertEquals(owner, taskModel.getOwner());
    }

    @Test
    public void shouldCreateAddATask() throws ExecutionException, InterruptedException {
        final String title = "Some title";
        final String description = "Some description";
        final String owner = "Some owner";
        final String projectId = "Some projectId";

        final TaskModel model = new TaskModel(
                title,
                description,
                false,
                new Date(),
                new Date(),
                owner,
                projectId
        );


        TaskModel expected = dao.create(model);

        TaskModel taskModel = dao.findById(expected.getId());

        Assertions.assertNotNull(taskModel);
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expected, taskModel));
    }
}
