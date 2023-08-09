package com.atos.camunda.controllers;

import com.atos.camunda.model.Lieu;
import com.atos.camunda.model.PauseDej;
import com.atos.camunda.model.Variable;
import com.atos.camunda.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/aller-manger")
public class PauseDejController {

    private final OrderService orderService;

    public PauseDejController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PauseDej iWantToEat(@RequestBody PauseDej data) {
        return orderService.wantToEat(data.getMangerId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PauseDej> getAllProcess() {
        return orderService.getAll();
    }

    @PostMapping("/{mangerId}/{taskKey}/complete")
    public PauseDej completeTask(@PathVariable String mangerId,
                                                 @PathVariable String taskKey,
                                                 @RequestBody Variable variable) {
        return orderService.completeTask(mangerId, taskKey, variable);

    }

    @GetMapping("/{businessKey}")
    public ResponseEntity<PauseDej> getProcess(@PathVariable String businessKey) {
        return orderService.getByBusinessKey(businessKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{businessKey}/signal")
    public ResponseEntity<PauseDej> signal(@PathVariable String businessKey) {
        return orderService.signal(businessKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{businessKey}/message")
    public ResponseEntity<PauseDej> message(@PathVariable String businessKey) {
        return orderService.message(businessKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{businessKey}/lieu")
    public ResponseEntity<PauseDej> addLieu(@PathVariable String businessKey, @RequestBody Lieu lieu) {
        return orderService.setLieu(businessKey, lieu)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{businessKey}/order")
    public ResponseEntity<PauseDej> order(@PathVariable String businessKey, @RequestBody Lieu lieu) {
        return orderService.order(businessKey, lieu)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}