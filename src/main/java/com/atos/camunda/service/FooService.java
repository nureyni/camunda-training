package com.atos.camunda.service;


import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FooService implements JavaDelegate {

    Logger log = LoggerFactory.getLogger(FooService.class);

    public void foo() {
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Foo method called");
        throw new BpmnError("ERROR_007", "No valid");
    }
}
