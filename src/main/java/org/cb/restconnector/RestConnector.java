package org.cb.restconnector;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.cb.restconnector.config.EndpointConfig;

public class RestConnector {
    private final EndpointConfig endpointConfig;
    private final HttpRequestFactory httpRequestFactory;

    public RestConnector(final EndpointConfig endpointConfig) {
        this.endpointConfig = endpointConfig;
        this.httpRequestFactory = new NetHttpTransport().createRequestFactory();
    }

    public RestConnectorResponse invoke(Action action) {
        return null;
    }

    public RestConnectorResponse invoke(String resource,Action action) {
        return null;
    }
}
