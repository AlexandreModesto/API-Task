package com.task.api.Task.model;

import java.util.Date;

public record TaskDTO(String name, double cost, Date dateLimit) {
}
