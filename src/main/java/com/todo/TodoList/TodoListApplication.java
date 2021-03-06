package com.todo.TodoList;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(TodoListApplication.class, args);
		} catch(Throwable ex) {
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void initialize() {
		try {

			ClassPathResource serviceAccount =
					new ClassPathResource("todo-list-bc3ad-firebase-adminsdk-zliph-2a7c1de0f2.json");

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
