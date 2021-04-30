package org.cb.restconnector;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
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

  public RestConnectorResponse invoke(Action action, String rawInput) throws IOException {
    HttpRequest request = null;

    switch (action) {
      case GET:
        request =
            httpRequestFactory
                .buildGetRequest(this.url)
                .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
      case POST:
        request =
            httpRequestFactory
                .buildPostRequest(this.url, rawInput)
                .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
        break;
      case PUT:
        request =
            httpRequestFactory
                .buildPatchRequest(this.url, rawInput)
                .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
        break;
      case PATCH:
        request =
            httpRequestFactory
                .buildPatchRequest(this.url,rawInput)
                .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
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
