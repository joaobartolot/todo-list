package com.todo.TodoList.dao;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.todo.TodoList.model.*;
import org.springframework.stereotype.Component;

@Component
public class TaskDao {

    public ArrayList<TaskModel> database = new ArrayList<TaskModel>();

    public static final String COLLECTION_NAME="task";

    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    public TaskModel create(String title, String description, String owner) throws ExecutionException, InterruptedException {
        TaskModel model = new TaskModel(
                title,
                description,
                false,
                new Date(),
                new Date(),
                owner
        );

        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).add(model).get();

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        return future.get().toObject(TaskModel.class);
    }

    public TaskModel findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return future.get().toObject(TaskModel.class);
    }

    public void update(TaskModel taskModel) {


    }
}
