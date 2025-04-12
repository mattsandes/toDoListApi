package org.com.sandes;

import org.com.sandes.model.Task;
import org.com.sandes.repositories.TaskRepository;
import org.com.sandes.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ToDoListApplicationTests {

	@InjectMocks
	private TaskService service;

	@Mock
	private TaskRepository repository;

	Task task;

	@BeforeEach
	void setUp() {
		task = new Task(
				UUID.fromString("a89fe73a-2bb7-4dfc-ab19-c33aee7a4574"),
				"Test Task name",
				"Test Task description",
				LocalDateTime.now(),
				false);
	}

	@DisplayName("Verificar que é possivel criar uma tarefa")
	@Test
	void createTask_ShouldCreateTasks(){
		given(repository.save(task)).willReturn(task);

		var resultado = service.createTask(task);

		assertNotNull(task);
		assertEquals("Test Task name", resultado.getTitulo());
		assertEquals("Test Task description", resultado.getDescricao());
		assertNotNull(resultado.getDataCriacao());
		assertEquals(false, resultado.getConcluida());
	}

	@DisplayName("Verificar que é possivel listar uma tarefa")
	@Test
	void findAll_ShouldReturnTasks(){
	    //Given (Arrange)
		given(repository.save(task)).willReturn(task);

	    //When (Act);
		var createTask = service.createTask(task);

		var foundTask = service.findAll();

	    //Then (Assert)
		assertNotNull(foundTask);
	}

	@DisplayName("Verificar que o usuário consegue encontrar uma tarefa pelo ID")
	@Test
	void findById_ShouldReturnTask_WhenSearchByID() {
		given(repository.save(task)).willReturn(task);
		given(repository.findById(task.getId())).willReturn(Optional.of(task));

		service.createTask(task);

		var result = service.findById(task.getId());

		assertNotNull(result);
		assertEquals("Test Task name", result.getTitulo());
		assertEquals("Test Task description", result.getDescricao());
	}
}
