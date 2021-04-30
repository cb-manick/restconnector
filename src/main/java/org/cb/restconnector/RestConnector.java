package org.cb.restconnector;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.cb.restconnector.config.EndpointConfig;

import java.io.IOException;

public class RestConnector {
  private final EndpointConfig endpointConfig;
  private final HttpRequestFactory httpRequestFactory;
  private final GenericUrl url;

  /** @param endpointConfig */
  public RestConnector(final EndpointConfig endpointConfig) {
    this.endpointConfig = endpointConfig;
    this.url = new GenericUrl();
    this.url.setHost(endpointConfig.getHost());
    this.url.setScheme(endpointConfig.getScheme().toString());
    this.httpRequestFactory = new NetHttpTransport().createRequestFactory();
  }

    public RestConnectorResponse invoke(Action action) {
        return null;
    }

  public RestConnectorResponse invoke(String resource, Action action) {
    return null;
  }
}
