package org.com.sandes.services;

import org.com.sandes.exceptions.ResourceNotFoundException;
import org.com.sandes.model.Task;
import org.com.sandes.model.dtos.TaskDTO;
import org.com.sandes.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository repository;
    final private TaskDTO dto = new TaskDTO();

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskDTO> findAll() {
        List<TaskDTO> dtos = new ArrayList<>();

        var entities = repository.findAll();

        for(Task entity: entities) {
            dto.setID(entity.getId());
            dto.setTitulo(entity.getTitulo());
            dto.setDescricao(entity.getDescricao());
            dto.setDataCriacao(entity.getDataCriacao());
            dto.setConcluida(entity.getConcluida());

            dtos.add(dto);
        }

        return dtos;
    }

    public TaskDTO findById(UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No records found for this ID"));

        dto.setID(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setConcluida(entity.getConcluida());

        return dto;
    }

    public TaskDTO createTask(TaskDTO dto) {
        if(dto == null) {
            throw new IllegalArgumentException("Object body can't be null");
        }

        Task task = new Task();

        task.setTitulo(dto.getTitulo());
        task.setDescricao(dto.getDescricao());
        task.setConcluida(dto.getConcluida());

        repository.save(task);

        TaskDTO returnDto = new TaskDTO();

        returnDto.setID(task.getId());
        returnDto.setTitulo(task.getTitulo());
        returnDto.setDescricao(task.getDescricao());
        returnDto.setDataCriacao(task.getDataCriacao());
        returnDto.setConcluida(task.getConcluida());

        return returnDto;
    }

    public TaskDTO updateTask(TaskDTO task) {
        if(task == null) {
            throw new IllegalArgumentException("Object body can't be null");
        }

        var foundTask = repository.findById(
                task.getId()).orElseThrow(() -> new ResourceNotFoundException("Couldn't find any records for this id!"));

        foundTask.setTitulo(task.getTitulo());
        foundTask.setDescricao(task.getDescricao());
        foundTask.setConcluida(task.getConcluida());

        repository.save(foundTask);

        TaskDTO returnDto = new TaskDTO();

        returnDto.setID(foundTask.getId());
        returnDto.setTitulo(foundTask.getTitulo());
        returnDto.setDescricao(foundTask.getDescricao());
        returnDto.setDataCriacao(foundTask.getDataCriacao());
        returnDto.setConcluida(foundTask.getConcluida());

        return returnDto;
    }

    public ResponseEntity<?> deleteTask(UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("Object body can't be null");
        }

        var foundTask = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find any task by this ID"));

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
