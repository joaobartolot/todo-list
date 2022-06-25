package com.todo.TodoList.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.todo.TodoList.model.ProjectModel;
import com.todo.TodoList.model.TaskModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Component
public class ProjectDao {

    public ArrayList<TaskModel> database = new ArrayList<TaskModel>();

    public static final String COLLECTION_NAME = "project";

    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    public ProjectModel create(String name) throws ExecutionException, InterruptedException {

        ProjectModel model = new ProjectModel(
                name,
                new Date(),
                new Date()
        );

        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).add(model).get();
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return future.get().toObject(ProjectModel.class);
    }

    public ProjectModel findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return future.get().toObject(ProjectModel.class);
    }
}
