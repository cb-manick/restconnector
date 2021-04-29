package org.cb.restconnector;

import org.junit.Assert;
import org.junit.Test;

public class RestConnectorTest {

    @Test
    public void RestConnectorCreation() {
        RestConnector restConnector = new RestConnector();
        Assert.assertNotNull(restConnector);
    }


}
