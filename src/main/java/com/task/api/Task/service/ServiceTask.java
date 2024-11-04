package com.task.api.Task.service;

import com.task.api.Task.model.Task;
import com.task.api.Task.repository.RepositoryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTask  {

    @Autowired
    private RepositoryTask repository;

    public Optional<Task> getInfo(Long id){
        return repository.findById(id);
    }

    public Optional<Task> checkPosition(Integer position){
        return repository.findByPosition(position);
    }

    public Optional<Task> checkName(String name){
        return repository.findByName(name);
    }

    public List<Task> getAll(){
        return repository.findAll();
    }

    public void register(Task obj){
        repository.save(obj);
    }
    public void remove(Task obj){
        repository.delete(obj);
    }
}
