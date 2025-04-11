package org.com.sandes.controllers;

import org.com.sandes.model.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {

    final private List<Task> tasks = new ArrayList<>();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody Task task) {
        if (task == null) {
            throw new RuntimeException("A tarefa não pode ser vazia!");
        }

        task.setId(UUID.randomUUID());
        task.setDataCriacao(new Date());

        tasks.add(task);

        return task;
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        if (task == null) {
            throw new RuntimeException("A tarefa não pode ser vazia!");
        }

        for(Task aimTask: tasks) {
            if(aimTask.getId().equals(task.getId())) {

                aimTask.setTitulo(task.getTitulo());
                aimTask.setDescricao(task.getDescricao());

                return aimTask;
            }
        }

        return null;
    }

    @GetMapping(value = "/{id}")
    public Task findById(@PathVariable UUID id) {
        if(id == null) {
            throw new RuntimeException("O id não pode ser nulo para a pesquisa!");
        }

        for(Task aimTask: tasks) {
            if(aimTask.getId().equals(id)) {

                return aimTask;
            }
        }

        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTasks(@PathVariable UUID id) {
        if(id == null) {
            throw new RuntimeException("O id não pode ser nulo para a pesquisa!");
        }

        for(Task aimTask: tasks) {
            if(aimTask.getId().equals(id)) {

                tasks.remove(aimTask);

                System.out.println("Tarefa removida com sucesso!");

                return ResponseEntity.noContent().build();
            }
        }

        return null;
    }
}
