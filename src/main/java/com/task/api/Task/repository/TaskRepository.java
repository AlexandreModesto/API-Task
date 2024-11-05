package com.task.api.Task.repository;

import com.task.api.Task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t WHERE t.OrdemDeApresentacao=:position")
    public Task findByPosition(Long position);

    @Query("SELECT t FROM Task t WHERE t.NomeDaTarefa=:name")
    public Task findByName(String name);

    @Query("SELECT t FROM Task t ORDER BY t.OrdemDeApresentacao ASC")
    public List<Task> findAllByOrderByOrdemDeApresentacaoASC();

    @Query("UPDATE Task t SET t.OrdemDeApresentacao=:newPosition WHERE t.id=:id")
    public void setPosition(Long newPosition,Long id);
}
