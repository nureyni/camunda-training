package com.atos.camunda.service;

import com.atos.camunda.model.Lieu;
import com.atos.camunda.model.PauseDej;
import com.atos.camunda.model.Variable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    PauseDej wantToEat(String businessKey);

    List<PauseDej> getAll();

    Optional<PauseDej> getByBusinessKey(String businessKey);

    Optional<PauseDej> signal(String businessKey);

    Optional<PauseDej> message(String businessKey);

    Optional<PauseDej> setLieu(String businessKey, Lieu lieu);

    Optional<PauseDej> order(String businessKey, Lieu lieu);

    PauseDej completeTask(String mangerId, String taskKey, Variable variable);
}
