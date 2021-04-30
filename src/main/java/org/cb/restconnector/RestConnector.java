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

  public RestConnectorResponse invoke(Action action, String json) throws IOException {
    JsonHttpContent content = new JsonHttpContent(this.jsonFactory, json);
    HttpRequest request = null;
    RestConnectorResponse response = new RestConnectorResponse();
    try {
      switch (action) {
        case GET:
          request =
              httpRequestFactory
                  .buildGetRequest(this.url)
                  .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                  .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
          break;
        case POST:
          request =
              httpRequestFactory
                  .buildPostRequest(this.url, content)
                  .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                  .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());

          break;
        case PUT:
        case PATCH:
          request =
              httpRequestFactory
                  .buildPatchRequest(this.url, content)
                  .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                  .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
          break;
        case DELETE:
          request =
              httpRequestFactory
                  .buildDeleteRequest(this.url)
                  .setConnectTimeout(this.endpointConfig.getConnectTimeoutInSec())
                  .setReadTimeout(this.endpointConfig.getRequestTimeoutInSec());
          break;
      }
    } catch (IOException e) {
      response.setSuccess(false);
      response.setErrorMessage(e.getMessage());
      return response;
    }
    response = executeRequest(request, response);
    return response;
  }

  private RestConnectorResponse executeRequest(
      HttpRequest request, RestConnectorResponse response) {
    try {
      HttpResponse httpResponse = request.execute();
      String rawString = httpResponse.parseAsString();
      response.setRawResponse(rawString);
      response.setSuccess(true);
      response.setHttpResponseCode(Integer.toString(httpResponse.getStatusCode()));
      HttpHeaders headers = httpResponse.getHeaders();
      /// TODO : get and set headers
    } catch (Exception ex) {
      response.setSuccess(false);
      response.setErrorMessage(ex.getMessage());
    }

    return response;
  }
}
