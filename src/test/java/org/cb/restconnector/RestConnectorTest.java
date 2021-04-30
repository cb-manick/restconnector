package org.cb.restconnector;

import org.cb.restconnector.config.EndpointConfig;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RestConnectorTest {

  @Test
  public void RestConnectorCreation() {
    EndpointConfig endpointConfig =
        new EndpointConfig("http://www.example.org", 10, 10, Scheme.HTTP);
    RestConnector restConnector = new RestConnector(endpointConfig);
    Assert.assertNotNull(restConnector);
  }

  @Test
  public void RestConnectTestConnect() throws IOException {
    EndpointConfig endpointConfig =
        new EndpointConfig("www.example.com", 1000, 1000, Scheme.HTTP);
    RestConnector restConnector = new RestConnector(endpointConfig);
    final RestConnectorResponse response = restConnector.invoke(Action.GET,"");
    Assert.assertTrue(response.isSuccess());
  }
}
