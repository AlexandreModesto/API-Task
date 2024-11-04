package com.task.api.Task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.TimeZone;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String NomeDaTarefa;

    @Column
    private double Custo;

    @Column
    private LocalDate DataLimite;

    @Column
    private Long OrdemDeApresentacao;


    public Task(String name, double cost, String dateLimitS) {
        this.NomeDaTarefa = name;
        this.Custo = cost;
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        LocalDate dateLimit = LocalDate.parse(dateLimitS);
        LocalDate dt = LocalDate.of(dateLimit.getYear(), dateLimit.getMonth(), dateLimit.getDayOfMonth());
        this.DataLimite = dt;
    }

    @PostPersist
    private void atribuirOrdemDeApresentacao() {
        this.OrdemDeApresentacao = this.id;
    }
}