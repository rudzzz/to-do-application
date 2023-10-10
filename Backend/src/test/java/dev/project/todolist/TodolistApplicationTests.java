package dev.project.todolist;

import dev.project.todolist.entities.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testCreateTodoSuccess() {
		var todo = new ToDo("dev task 1", "change the layout", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].completed").isEqualTo(todo.getCompleted())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	public void testCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
					new ToDo("", "", false, 0)
				).exchange()
				.expectStatus().isBadRequest();
	}

}
