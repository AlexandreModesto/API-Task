package com.task.api.Task.repository;

import com.task.api.Task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t WHERE t.OrdemDeApresentacao=:position")
    public Task findByPosition(Integer position);

    @Query("SELECT t FROM Task t WHERE t.NomeDaTarefa=:name")
    public Task findByName(String name);
}
