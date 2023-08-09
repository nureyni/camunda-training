package com.atos.camunda.model;

import java.util.HashMap;
import java.util.Map;

public class Variable {

    private Map<String, Object> variables = new HashMap<>();

    public Variable(Map<String, Object> variables) {
        this.variables = variables;
    }

    public Variable() {
    }


    public Map<String, Object> getVariables() {
        return variables;
    }
}
