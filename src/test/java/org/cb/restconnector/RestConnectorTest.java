package org.cb.restconnector;

import org.cb.restconnector.config.EndpointConfig;
import org.junit.Assert;
import org.junit.Test;

public class RestConnectorTest {

  @Test
  public void RestConnectorCreation() {
    EndpointConfig endpointConfig =
        new EndpointConfig("http://www.example.org", 10, 10, Scheme.HTTP);
    RestConnector restConnector = new RestConnector(endpointConfig);
    Assert.assertNotNull(restConnector);
  }

  @Test
  public void RestConnectTestConnect() {
    EndpointConfig endpointConfig =
        new EndpointConfig("http://www.example.org", 10, 10, Scheme.HTTP);
    endpointConfig.setHost("http://www.example.org");
    RestConnector restConnector = new RestConnector(endpointConfig);
    final RestConnectorResponse response = restConnector.invoke(Action.GET);
    Assert.assertTrue(response.isSuccess());
  }
}
