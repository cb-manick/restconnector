package org.cb.restconnector;

import org.cb.restconnector.action.ActionExecutionStrategyFactory;
import org.cb.restconnector.action.ActionRestExecutionStrategy;
import org.cb.restconnector.config.RestEndpointConfig;

import java.io.IOException;

public class RestConnector {
  private final RestEndpointConfig endpointConfig;
  /** @param endpointConfig */
  RestConnector(final RestEndpointConfig endpointConfig) {
    this.endpointConfig = endpointConfig;
  }

  public RestConnectorResponse invokeActionOnResource(RestConnectorRequest restConnectorRequest)
      throws IOException {
    ActionRestExecutionStrategy restExecutionStrategy =
        ActionExecutionStrategyFactory.getRestExecutionStrategy(restConnectorRequest.getAction());
    return restExecutionStrategy.invokeAction(restConnectorRequest, endpointConfig);
  }
}
