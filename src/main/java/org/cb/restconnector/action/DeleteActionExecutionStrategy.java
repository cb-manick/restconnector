package org.cb.restconnector.action;

import com.google.api.client.http.HttpRequest;
import org.cb.restconnector.RestConnectorRequest;
import org.cb.restconnector.config.RestEndpointConfig;

import java.io.IOException;

public class DeleteActionExecutionStrategy extends ActionRestExecutionStrategy {

  @Override
  protected HttpRequest configureHttpRequest(
      RestConnectorRequest request, RestEndpointConfig config) throws IOException {
    return this.httpRequestFactory.buildDeleteRequest(getGenericUrl(config));
  }
}
