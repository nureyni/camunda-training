package com.atos.camunda;

import com.atos.camunda.service.OrderService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowTest extends AbstractProcessEngineRuleTest {

  @Autowired
  public RuntimeService runtimeService;

  @Autowired
  public TaskService taskService;

  @Autowired
  private OrderService orderService;

  @Test
  public void shouldExecuteHappyPath() {
    // given
    String businessKey = "test";

    // when

    orderService.wantToEat(businessKey);
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
            .processInstanceBusinessKey(businessKey)

            .singleResult();

    long count = runtimeService.createExecutionQuery()
            .count();

    // then
    assertThat(processInstance)
            .isStarted()
            .task(taskService.createTaskQuery()
                    .processInstanceBusinessKey(businessKey))
            .hasDefinitionKey("ChoixLieu");
  }

}
