package com.todo.TodoList.dao;

import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.todo.TodoList.model.*;
import org.springframework.stereotype.Component;

@Component
public class TaskDao {
    public static final String COLLECTION_NAME = "task";

    private final Firestore dbFirestore = FirestoreClient.getFirestore();

    public TaskModel create(TaskModel model) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).add(model).get();

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        return future.get().toObject(TaskModel.class);
    }

    public TaskModel findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return future.get().toObject(TaskModel.class);
    }

    public ArrayList<TaskModel> findByProjectId(String id) throws ExecutionException, InterruptedException {
        ArrayList<TaskModel> taskList = new ArrayList<>();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference.whereEqualTo("projectId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            taskList.add(document.toObject(TaskModel.class));
        }
        return taskList;
    }


    public TaskModel update(TaskModel taskModel) throws ExecutionException, InterruptedException  {
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(taskModel.getId());
        documentReference.set(taskModel).get();

        ApiFuture<DocumentSnapshot> snapshot = documentReference.get();

        return snapshot.get().toObject(TaskModel.class);
    }

    public void delete(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        documentReference.delete().get();
    }
}
