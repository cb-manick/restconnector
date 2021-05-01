package org.cb.restconnector.action;

import org.cb.restconnector.RestConnector;
import org.cb.restconnector.RestConnectorRequest;
import org.cb.restconnector.RestConnectorResponse;

public abstract class ActionRestExecutionStrategy {
    public abstract RestConnectorResponse invokeAction(RestConnectorRequest request);
}
