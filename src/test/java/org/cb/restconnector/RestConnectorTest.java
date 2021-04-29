package org.cb.restconnector;

import org.cb.restconnector.config.EndpointConfig;
import org.junit.Assert;
import org.junit.Test;

public class RestConnectorTest {

    @Test
    public void RestConnectorCreation() {
        RestConnector restConnector = new RestConnector(new EndpointConfig());
        Assert.assertNotNull(restConnector);
    }

    @Test
    public void RestConnecTestConnect() {
        RestConnector restConnector = new RestConnector(new EndpointConfig());
        restConnector.test("www.example.org");
        Assert.assertNotNull(restConnector);
    }


}
