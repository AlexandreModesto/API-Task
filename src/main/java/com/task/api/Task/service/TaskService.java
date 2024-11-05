package com.task.api.Task.service;

import com.task.api.Task.model.Task;
import com.task.api.Task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Optional<Task> getInfo(Long id){
        return repository.findById(id);
    }

    public Optional<Task> checkPosition(Long position){
        Task task=repository.findByPosition(position);
        return Optional.ofNullable(task);
    }

    public Optional<Task> checkName(String name){
        Task task =repository.findByName(name);
        return Optional.ofNullable(task);
    }

    public List<Task> getAll(){
        return repository.findAllByOrderByOrdemDeApresentacaoASC();
    }

    public void register(Task obj){
        repository.save(obj);
    }
    public void remove(Task obj){
        repository.delete(obj);
    }
}
