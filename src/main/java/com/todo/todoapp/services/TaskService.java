package com.todo.todoapp.services;

import com.todo.todoapp.entities.Task;
import com.todo.todoapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> findAllCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }

    public List<Task> findAllUncompletedTasks() {
        return taskRepository.findByCompletedFalse();
    }



}
