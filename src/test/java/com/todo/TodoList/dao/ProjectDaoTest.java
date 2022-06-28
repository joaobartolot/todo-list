package com.todo.TodoList.dao;

import com.todo.TodoList.model.ProjectModel;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectDaoTest {

    @Autowired
    private ProjectDao dao;

    @Test
    public void shouldUpdateProject() throws ExecutionException, InterruptedException {
        final ProjectModel expected = new ProjectModel();
        expected.setId("4FhBDWlBCy2Uq2i6jbXc");
        expected.setName("testing diff");

        final ProjectModel actual = dao.update(expected);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void shouldDeleteProject() throws ExecutionException, InterruptedException {
        final String projectId = "Some projectId";

        dao.delete(projectId);

        Assertions.assertNull(dao.findById(projectId));
    }
}
