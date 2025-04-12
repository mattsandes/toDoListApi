package org.com.sandes.services;

import org.apache.coyote.Response;
import org.com.sandes.model.Task;
import org.com.sandes.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(UUID id) {
        if(id == null) {
            throw new RuntimeException("The id can't be null");
        }

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));
    }

    public Task createTask(Task task) {
        if(task.getTitulo() == null || task.getDescricao() == null) {
            throw new RuntimeException("Neither title or description can be null");
        }

        return repository.save(task);
    }

    public Task updateTask(Task task) {
        if(task == null) {
            throw new RuntimeException("Task can't be null");
        }

        var foundTask = repository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Any task was found by this ID."));

        foundTask.setTitulo(task.getTitulo());
        foundTask.setDescricao(task.getDescricao());
        foundTask.setConcluida(task.getConcluida());

        return repository.save(foundTask);
    }

    public ResponseEntity<?> deleteTask(UUID id) {
        if(id == null) {
            throw new RuntimeException("Id can't be null");
        }

        var foundTask = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find any task by this ID"));

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
