package org.cb.restconnector.action;

import org.junit.Assert;
import org.junit.Test;

public class ActionExecutionStrategyFactoryTest {
  @Test
  public void TestActionExecutionStrategyFactory() {
     Assert.assertTrue(ActionExecutionStrategyFactory.getRestExecutionStrategy(Action.GET) instanceof GetActionRestExecutionStrategy);
     Assert.assertTrue(ActionExecutionStrategyFactory.getRestExecutionStrategy(Action.POST) instanceof PostActionExecutionStrategy);
     Assert.assertTrue(ActionExecutionStrategyFactory.getRestExecutionStrategy(Action.PUT) instanceof PutOrPatchActionExecutionStrategy); ;
     Assert.assertTrue(ActionExecutionStrategyFactory.getRestExecutionStrategy(Action.DELETE) instanceof DeleteActionExecutionStrategy);
  }
}
