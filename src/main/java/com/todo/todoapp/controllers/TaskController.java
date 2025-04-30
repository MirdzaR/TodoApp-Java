package com.todo.todoapp.controllers;

import com.todo.todoapp.entities.Task;
import com.todo.todoapp.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasksList = taskService.getAllTasks();
        return new ResponseEntity<>(tasksList, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        List<Task> taskList = taskService.findAllCompletedTasks();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/uncompleted")
    public ResponseEntity<List<Task>> getAllUncompletedTasks() {
        List<Task> taskList = taskService.findAllUncompletedTasks();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Task foundTask = taskService.findTaskById(id);
        if (foundTask != null) {
            return new ResponseEntity<>(foundTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
       Task createdTask = taskService.createNewTask(task);
       return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully!");
    }
}
