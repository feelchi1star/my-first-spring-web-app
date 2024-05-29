package com.springwebapp.myfirstspringwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {

	private TodoService todoService;
	private TodoRepository todoRepository;

	public TodoControllerJPA(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}

	//
	@GetMapping(value = "list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedIn(model);

		// List<Todo> todos = todoService.findByUserName(username);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.put("todos", todos);
		return "listTodos";
	}

	// /
	@GetMapping(value = "add-todo")
	public String showNewTodoPage(ModelMap model) {
		String username = (String) model.get("name");

		Todo todo = new Todo(5, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);

		return "todo";
	}

	@PostMapping("add-todo")
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		String username = getLoggedIn(model);
		todo.setUsername(username);
		todoRepository.save(todo);

		System.out.println(todo.toString());

		// todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(),
		// todo.isDone());

		return "redirect:list-todos";
	}

	@GetMapping("delete-todo/{id}")
	public String deleteMethod(@PathVariable int id) {
		// todoService.deleteById(id);
		todoRepository.deleteById(id);
		return "redirect:/list-todos";
	}

	@GetMapping("update-todo/{id}")
	public String showUpdateTodo(@PathVariable int id, ModelMap model) {
		// Todo todo = todoService.findById(id);
		Todo todo = todoRepository.findById(id).get();

		model.put("todo", todo);
		return "todo";
	}

	@PostMapping("update-todo/{id}")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedIn(model);
		todo.setUsername(username);
		// todoService.updateTodo(todo);
		todoRepository.save(todo);
		return "redirect:/list-todos";
	}

	private String getLoggedIn(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
