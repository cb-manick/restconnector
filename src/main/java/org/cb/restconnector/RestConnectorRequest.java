package org.cb.restconnector;

import org.cb.restconnector.action.Action;

public class RestConnectorRequest {
    private final Action action;
    private final String json;

    public RestConnectorRequest(Action action, String json) {
        this.action = action;
        this.json = json;
    }

    public Action getAction() {
        return action;
    }

    public String getJson() {
        return json;
    }
}
