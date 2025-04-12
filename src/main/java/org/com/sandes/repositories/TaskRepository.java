package org.com.sandes.repositories;

import org.com.sandes.model.Task;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
