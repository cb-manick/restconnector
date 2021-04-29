package org.cb.restconnector;

public enum Action {
    GET("get"),
    POST("post"),
    PUT("put"),
    PATCH("patch"),
    DELETE("delete");

    private String action;
    Action(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Action{" +
                "action='" + action + '\'' +
                '}';
    }
}
