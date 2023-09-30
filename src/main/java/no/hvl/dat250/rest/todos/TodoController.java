package no.hvl.dat250.rest.todos;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest-Endpoint for todos.
 */

@RestController
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  private ArrayList<Todo> todoList = new ArrayList<Todo>();


  //Get all Todos
  @GetMapping("/todos")
  public ArrayList<Todo> getTodos() {
    return todoList;
  }

  //Get one Todo by its ID
  @GetMapping("/todos/{todoID}")
  public Todo getOneTodo(@PathVariable Long todoID) {
    for (Todo todo : todoList) {
      if (todo.getId().equals(todoID)) {
        return todo;
      }
    }
    throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, todoID));
  }


  //Create a new Todo
  @PostMapping("/todos")
  public Todo create(@RequestBody Todo newTodo) {
    Todo todo = new Todo();
    todo.setDescription(newTodo.getDescription());
    todo.setSummary(newTodo.getSummary());
    todo.setId(new Random().nextLong());
    todoList.add(todo);
    return todo;
  }

  
  //Update a todo with a given ID
  @PutMapping("/todos/{todoID}")
  public Todo updateTodo(@PathVariable Long todoID, @RequestBody Todo newTodo) {
    for (Todo todo : todoList) {
      if (todo.getId().equals(todoID)) {
        todo.setDescription(newTodo.getDescription());
        todo.setSummary(newTodo.getSummary());
        todo.setId(newTodo.getId());
        return todo;
      }
    }
    throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, todoID));
  }


  //Delete an existing Todo with a given ID
  @DeleteMapping("/todos/{todoID}")
  public Todo deleteOneTodo(@PathVariable Long todoID) {
    for (Todo todo : todoList) {
      if (todo.getId().equals(todoID)) {
        todoList.remove(todo);
        return todo;
      }
    }
    throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, todoID));
  }
}

