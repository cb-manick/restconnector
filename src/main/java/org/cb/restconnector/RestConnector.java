package org.cb.restconnector;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.cb.restconnector.config.EndpointConfig;

import java.io.IOException;

public class RestConnector {
  private final EndpointConfig endpointConfig;
  private final HttpRequestFactory httpRequestFactory;
  private final GenericUrl url;
  private final JsonFactory jsonFactory;

  /** @param endpointConfig */
  RestConnector(final EndpointConfig endpointConfig) {
    this.endpointConfig = endpointConfig;
    this.url = new GenericUrl();
    this.url.setHost(endpointConfig.getHost());
    this.url.setScheme(endpointConfig.getScheme().name());
    this.httpRequestFactory = new NetHttpTransport().createRequestFactory();
    this.jsonFactory = new JacksonFactory();
  }

  public RestConnectorResponse request(Action action, String json) {
    JsonHttpContent content = new JsonHttpContent(this.jsonFactory, json);
    HttpRequest request = null;
    try {
      switch (action) {
        case GET:
          request = getGetRequest();
          break;
        case POST:
          request = getPostRequest(content);
          break;
        case PUT:
        case PATCH:
          request = getPutOrPatchRequest(content);
          break;
        case DELETE:
          request = getDeleteRequest();
          break;
      }
    } catch (IOException e) {
      return getRestConnectorErrorResponse(e);
    }
    RestConnectorResponse restConnectorResponse = makeRequest(request);
    return restConnectorResponse;
  }

  private RestConnectorResponse getRestConnectorErrorResponse(IOException e) {
    RestConnectorResponse restConnectorResponse = new RestConnectorResponse();
    restConnectorResponse.setSuccess(false);
    restConnectorResponse.setErrorMessage(e.getMessage());
    return restConnectorResponse;
  }

  private HttpRequest getDeleteRequest() throws IOException {
    return httpRequestFactory
        .buildDeleteRequest(this.url)
        .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
        .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
  }

  private HttpRequest getPutOrPatchRequest(JsonHttpContent content) throws IOException {
    return httpRequestFactory
        .buildPatchRequest(this.url, content)
        .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
        .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
  }

  private HttpRequest getPostRequest(HttpContent content) throws IOException {
    return httpRequestFactory
        .buildPostRequest(this.url, content)
        .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
        .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
  }

  private HttpRequest getGetRequest() throws IOException {
    return httpRequestFactory
        .buildGetRequest(this.url)
        .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
        .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
  }

  private RestConnectorResponse makeRequest(HttpRequest request) {

    try {
      HttpResponse httpResponse = request.execute();
      return getRestConnectorResponse(httpResponse);
      /// TODO : get and set headers
    } catch (Exception ex) {
     return getRestConnectorErrorResponse(ex);
    }

  }

  private RestConnectorResponse getRestConnectorResponse(HttpResponse httpResponse) throws IOException {
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
}
