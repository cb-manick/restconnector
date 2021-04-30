package org.cb.restconnector;

public enum Scheme {
    HTTP("http"),
    HTTPS("https");

    private final String scheme;

    Scheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "scheme='" + scheme + '\'' +
                '}';
    }
}
