package org.com.sandes;

import org.com.sandes.model.Task;
import org.com.sandes.model.dtos.TaskDTO;
import org.com.sandes.repositories.TaskRepository;
import org.com.sandes.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ToDoListApplicationTests {

	@InjectMocks
	private TaskService service;

	@Mock
	private TaskRepository repository;

	Task task;
	TaskDTO dto;

	@BeforeEach
	void setUp() {
		dto = new TaskDTO();
		task = new Task(
				UUID.fromString("a3f62103-c0dc-4747-8e5b-dec3883c69b9"),
				"Title test",
				"Description test",
				LocalDateTime.now(),
				false);
	}

	@DisplayName("Verificar que é possivel criar uma tarefa")
	@Test
	void createTask_ShouldCreateTasks(){
		given(repository.save(any(Task.class))).willReturn(task);

		dto.setTitulo(task.getTitulo());
		dto.setDescricao(task.getDescricao());
		dto.setDataCriacao(task.getDataCriacao());

		var result = service.createTask(dto);

		assertNotNull(dto);
		assertEquals("Title test", result.getTitulo());
		assertEquals("Description test", result.getDescricao());
		assertEquals(false, result.getConcluida());
        assertSame(TaskDTO.class, result.getClass());
	}

	@DisplayName("Verificar que é possivel listar uma tarefa")
	@Test
	void findAll_ShouldReturnTasks(){

		List<Task> entities = new ArrayList<>();

		Task task1 = new Task(
				UUID.fromString("a3f62103-c0dc-4747-8e5b-dec3883c69b9"),
				"Title test",
				"Description test",
				LocalDateTime.now(),
				false);

		Task task2 = new Task(
				UUID.fromString("a3f62103-c0dc-4747-8e5b-dec3883c69b9"),
				"Title test",
				"Description test",
				LocalDateTime.now(),
				false);

		entities.add(task1);
		entities.add(task2);

		given(repository.findAll()).willReturn(entities);

		var dtos = service.findAll();
		var dto = entities.getFirst();

		assertNotNull(dtos);
	}

	@DisplayName("Verificar que o usuário consegue encontrar uma tarefa pelo ID")
	@Test
	void findById_ShouldReturnTask_WhenSearchByID() {
		given(repository.save(any(Task.class))).willReturn(task);
		given(repository.findById(task.getId())).willReturn(Optional.of(task));

		dto.setID(task.getId());
		dto.setTitulo(task.getTitulo());
		dto.setDescricao(task.getDescricao());
		dto.setDataCriacao(task.getDataCriacao());
		dto.setConcluida(task.getConcluida());

		service.createTask(dto);

		var result = service.findById(task.getId());

		assertNotNull(result);
		assertEquals("Title test", result.getTitulo());
		assertEquals("Description test", result.getDescricao());
	}

	@DisplayName("Verificar que o metodo deletar exclui objetos da base de dados")
	@Test
	void Delete_Should_RemoveObject_ByItsIds() {
		given(repository.save(any(Task.class))).willReturn(task);
		given(repository.findById(task.getId())).willReturn(Optional.of(task));
		doNothing().when(repository).deleteById(task.getId());

		dto.setID(task.getId());
		dto.setTitulo(task.getTitulo());
		dto.setDescricao(task.getDescricao());
		dto.setDataCriacao(task.getDataCriacao());
		dto.setConcluida(task.getConcluida());

		service.createTask(dto);

		var result = service.deleteTask(dto.getId());

		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}
}
