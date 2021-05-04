package org.cb.restconnector.action;

import java.util.HashMap;
import java.util.Map;
;

public class ActionExecutionStrategyFactory {
  private static Map<Action, ActionRestExecutionStrategy> map = new HashMap<>();

  static {
    map.put(Action.GET, new GetActionRestExecutionStrategy());
    map.put(Action.PUT, new PutOrPatchActionExecutionStrategy());
    map.put(Action.PATCH, new PutOrPatchActionExecutionStrategy());
    map.put(Action.POST, new PostActionExecutionStrategy());
    map.put(Action.DELETE, new DeleteActionExecutionStrategy());
  }

  public static ActionRestExecutionStrategy getRestExecutionStrategy(Action action) {
    return map.get(action);
  }
}
