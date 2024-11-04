package com.task.api.Task.repository;

import com.task.api.Task.model.Task;
import com.task.api.Task.service.ServiceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RepositoryTask extends JpaRepository<Task,Long> {

    @Query("SELECT * FROM Task WHERE t.position=:position")
    public Optional<Task> findByPosition(Integer position);

    @Query("SELECT * FROM Task WHERE t.name=:name")
    public Optional<Task> findByName(String name);
}
