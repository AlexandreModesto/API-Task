package com.task.api.Task.controller;

import com.task.api.Task.model.Task;
import com.task.api.Task.model.TaskDTO;
import com.task.api.Task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping(value = "task/")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> allTasks = service.getAll();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "info/{id}")
    public ResponseEntity<?> getTask(@PathVariable(value = "id")Long id){
        Optional<Task> task =service.getInfo(id);
        if(task.isPresent()){
            return ResponseEntity.ok(task);
        }else {return ResponseEntity.notFound().build();}
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> newTask(@RequestBody @Validated TaskDTO data){
        if(!service.checkName(data.name()).isPresent()){
            Task task =new Task(data.name(),data.cost(),data.dateLimit());
            service.register(task);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(value = "edit/{id}")
    public ResponseEntity<?> editTask(@RequestBody @Validated Task task, @PathVariable(value = "id")Long id){
        Optional<Task> oldTask = service.getInfo(id);
        if(oldTask.isPresent()){
            var newTask = oldTask.get();
            newTask.setNomeDaTarefa(task.getNomeDaTarefa());
            newTask.setCusto(task.getCusto());
            newTask.setDataLimite(task.getDataLimite());
            newTask.setOrdemDeApresentacao(task.getOrdemDeApresentacao());
            service.register(newTask);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("up/{id}")
    public ResponseEntity<?> upPosition(@PathVariable(value = "id")Long id){
        Optional<Task> task = service.getInfo(id);
        if(task.isPresent()){
            Optional<Task> task1 =service.checkPosition(id-1);
            task1.get().setOrdemDeApresentacao(id);
            task.get().setOrdemDeApresentacao(id-1);
            service.register(task.get());
            service.register(task1.get());
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("down/{id}")
    public ResponseEntity<?> downPosition(@PathVariable(value = "id")Long id){
        Optional<Task> task = service.getInfo(id);
        if(task.isPresent()){
            Optional<Task> task1 =service.checkPosition(id+1);
            task1.get().setOrdemDeApresentacao(id);
            task.get().setOrdemDeApresentacao(id+1);
            service.register(task.get());
            service.register(task1.get());
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id")Long id){
        Optional<Task> task = service.getInfo(id);
        if(task.isPresent()){
            service.remove(task.get());
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
