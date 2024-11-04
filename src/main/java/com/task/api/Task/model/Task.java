package com.task.api.Task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double cost;

    @Column
    private Date dateLimit;

    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer position;

    public Task(String name,double cost,Date dateLimit){
        this.name=name;
        this.cost=cost;
        this.dateLimit=dateLimit;
    }
}
