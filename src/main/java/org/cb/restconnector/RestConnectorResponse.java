package org.cb.restconnector;

import java.util.Map;

public class RestConnectorResponse {
    private boolean success;
    private String errorMessage;
    private String rawResponse;
    private String httpResponseCode;
    private Map<String,String> responseHeader;

    public RestConnectorResponse(String rawOutput, String httpResponseCode, Map<String, String> responseHeader) {
        this.rawResponse = rawOutput;
        this.httpResponseCode = httpResponseCode;
        this.responseHeader = responseHeader;
    }

    public RestConnectorResponse() {

    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    public String getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(String httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(Map<String, String> responseHeader) {
        this.responseHeader = responseHeader;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
