package org.cb.restconnector;

import org.cb.restconnector.action.Action;
import org.cb.restconnector.config.RestEndpointConfig;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RestConnectorTest {

  @Test
  public void RestConnectorCreation() {
    RestEndpointConfig endpointConfig =
        new RestEndpointConfig("http://www.example.org", 10, 10, Scheme.HTTP);
    RestConnector restConnector = new RestConnector(endpointConfig);
    Assert.assertNotNull(restConnector);
  }

  @Test
  public void RestConnectTestConnect() throws IOException {
    RestEndpointConfig endpointConfig =
        new RestEndpointConfig("www.example.com", 1000, 1000, Scheme.HTTP);
    RestConnector restConnector = new RestConnector(endpointConfig);
    final RestConnectorResponse response = restConnector.invokeActionOnResource(new RestConnectorRequest(Action.GET, null));
    Assert.assertTrue(response.isSuccess());
  }
}
