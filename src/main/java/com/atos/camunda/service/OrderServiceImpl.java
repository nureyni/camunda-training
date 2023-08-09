package com.atos.camunda.service;

import com.atos.camunda.model.Lieu;
import com.atos.camunda.model.PauseDej;
import com.atos.camunda.model.Variable;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    public static final String MANGER = "Manger";
    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public OrderServiceImpl(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @Override
    public PauseDej wantToEat(String businessKey) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Manger", businessKey);
        return mapFromProcessInstance(processInstance);
    }

    @Override
    public List<PauseDej> getAll() {
        return runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(MANGER)
                .list()
                .stream()
                .map(this::mapFromProcessInstance)
                .collect(Collectors.toList());
    }

    private PauseDej mapFromProcessInstance(ProcessInstance processInstance) {
        Map<String, Object> variables = runtimeService.getVariables(processInstance.getId());
        List<PauseDej.Tache> nextTache = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .list()
                .stream()
                .map(task -> new PauseDej.Tache(task.getId(), task.getTaskDefinitionKey()))
                .collect(Collectors.toList());
        return new PauseDej(processInstance.getBusinessKey(), variables, nextTache);
    }

    @Override
    public Optional<PauseDej> getByBusinessKey(String businessKey) {
        return Optional.empty();
    }

    @Override
    public Optional<PauseDej> signal(String businessKey) {
        return Optional.empty();
    }

    @Override
    public Optional<PauseDej> message(String businessKey) {
        return Optional.empty();
    }

    @Override
    public Optional<PauseDej> setLieu(String businessKey, Lieu lieu) {
        return Optional.empty();
    }

    @Override
    public Optional<PauseDej> order(String businessKey, Lieu lieu) {
        return Optional.empty();
    }

    @Override
    public PauseDej completeTask(String mangerId, String taskKey, Variable variable) {

        Task task = taskService.createTaskQuery()
                .taskDefinitionKey(taskKey)
                .processInstanceBusinessKey(mangerId)
                .singleResult();
        taskService.complete(task.getId(), variable.getVariables());
        return null;
    }
}
