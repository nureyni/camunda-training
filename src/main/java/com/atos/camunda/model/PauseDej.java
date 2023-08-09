package com.atos.camunda.model;

import java.util.List;
import java.util.Map;

public class PauseDej {
    private Map<String, Object> variables;
    private String mangerId;
    private List<Tache> nextTask;

    public PauseDej() {
    }

    public PauseDej(String businessKey, Map<String, Object> variables, List<Tache> nextTask) {
        this.mangerId = businessKey;
        this.variables = variables;
        this.nextTask = nextTask;
    }

    public String getMangerId() {
        return mangerId;
    }

    public void setMangerId(String mangerId) {
        this.mangerId = mangerId;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public List<Tache> getNextTask() {
        return nextTask;
    }

    public static class Tache {
        private String id;
        private String name;

        public Tache(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
