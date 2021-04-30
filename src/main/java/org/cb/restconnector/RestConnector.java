package org.cb.restconnector;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.cb.restconnector.config.EndpointConfig;

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
    HttpRequest request = null;

    switch (action) {
      case GET:
        break;
      case POST:
        break;
      case PUT:
        break;
      case PATCH:
        break;
      case DELETE:
        break;
    }
    RestConnectorResponse response = getResponse(request);
    return response;
  }

  private RestConnectorResponse getResponse(HttpRequest request) {
    RestConnectorResponse response = new RestConnectorResponse();
    try {
      HttpResponse execute = request.execute();
      String rawString = execute.parseAsString();
      response.setRawResponse(rawString);
      response.setSuccess(true);
      response.setHttpResponseCode(Integer.toString(execute.getStatusCode()));
      //      HttpHeaders headers = request.execute().getHeaders()
    } catch (Exception ex) {
      response.setSuccess(false);
      response.setErrorMessage(ex.getMessage());
    }

    return response;
  }

  public RestConnectorResponse invoke(String resource, Action action) {
    return null;
  }
}
