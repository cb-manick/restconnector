package org.cb.restconnector.action;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.cb.restconnector.RestConnectorRequest;
import org.cb.restconnector.RestConnectorResponse;
import org.cb.restconnector.config.RestEndpointConfig;

import java.io.IOException;

public abstract class ActionRestExecutionStrategy {
  protected final HttpRequestFactory httpRequestFactory;
  protected final JsonFactory jsonFactory;

  ActionRestExecutionStrategy() {
    this.httpRequestFactory = new NetHttpTransport().createRequestFactory();
    this.jsonFactory = new JacksonFactory();
  }

  public RestConnectorResponse invokeAction(
      final RestConnectorRequest restConnectorRequest, final RestEndpointConfig restEndpointConfig)
      throws IOException {
    final HttpRequest httpRequest = configureHttpRequest(restConnectorRequest, restEndpointConfig);
    configureHttpRequestCommonConfiguration(httpRequest, restEndpointConfig);
    return makeRequest(httpRequest);
  }

  private void configureHttpRequestCommonConfiguration(
      final HttpRequest httpRequest, final RestEndpointConfig restEndpointConfig) {
    httpRequest
        .setConnectTimeout(restEndpointConfig.getConnectTimeoutInSec())
        .setReadTimeout(restEndpointConfig.getRequestTimeoutInSec());
  }

  protected abstract HttpRequest configureHttpRequest(
      RestConnectorRequest request, RestEndpointConfig config) throws IOException;

  private RestConnectorResponse makeRequest(HttpRequest request) {

    try {
      HttpResponse httpResponse = request.execute();
      return getRestConnectorResponse(httpResponse);
    } catch (Exception ex) {
      return getRestConnectorErrorResponse(ex);
    }
  }

  private RestConnectorResponse getRestConnectorResponse(HttpResponse httpResponse)
      throws IOException {
    String rawString = httpResponse.parseAsString();
    RestConnectorResponse restConnectorResponse = new RestConnectorResponse();
    restConnectorResponse.setRawResponse(rawString);
    restConnectorResponse.setSuccess(true);
    restConnectorResponse.setHttpResponseCode(Integer.toString(httpResponse.getStatusCode()));
    HttpHeaders headers = httpResponse.getHeaders();
    return restConnectorResponse;
  }

  private RestConnectorResponse getRestConnectorErrorResponse(Exception ex) {
    RestConnectorResponse restConnectorResponse = new RestConnectorResponse();
    restConnectorResponse.setSuccess(false);
    restConnectorResponse.setErrorMessage(ex.getMessage());
    return restConnectorResponse;
  }

  protected GenericUrl getGenericUrl(RestEndpointConfig endpointConfig) {
    GenericUrl url = new GenericUrl();
    url.setHost(endpointConfig.getHost());
    url.setScheme(endpointConfig.getScheme().name());
    return url;
  }
}
