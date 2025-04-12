package org.com.sandes.controllers;

import org.com.sandes.model.Task;
import org.com.sandes.services.TaskService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Task findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        return service.updateTask(task);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTasks(@PathVariable UUID id) {
        return service.deleteTask(id);
    }
}
