package org.cb.restconnector.action;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.json.JsonHttpContent;
import org.cb.restconnector.RestConnectorRequest;
import org.cb.restconnector.RestConnectorResponse;
import org.cb.restconnector.config.RestEndpointConfig;

import java.io.IOException;

public class PutOrPatchActionExecutionStrategy extends ActionRestExecutionStrategy{

    @Override
    protected HttpRequest configureHttpRequest(RestConnectorRequest request, RestEndpointConfig config) throws IOException {
        HttpContent content = new JsonHttpContent(jsonFactory,request.getJson());
        return this.httpRequestFactory.buildPutRequest(getGenericUrl(config),content);
    }
}
