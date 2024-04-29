package com.springwebapp.myfirstspringwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service
public class TodoService {
	private static int todoCount = 0;

	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(++todoCount, "Feel", "Welomt to AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todoCount, "Musa", "Welomt to UI/UX", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todoCount, "John", "Welomt to AWS", LocalDate.now().plusYears(1), false));

	};

	public List<Todo> findByUserName(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}

	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todoCount, username, description, targetDate, done);
		todos.add(todo);
	}

	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		return todos.stream().filter(predicate).findFirst().get();
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
