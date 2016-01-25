package com.realexpayments.hpp;

/**
 * HPPError represents runtime exceptions
 */
public class HPPError extends RuntimeException{

    private final String url;

    public HPPError() {
        url = null;
    }

    public HPPError(String detailMessage, String url) {
        super(detailMessage);
        this.url = url;
    }

    public HPPError(String detailMessage, Throwable throwable, String url) {
        super(detailMessage, throwable);
        this.url = url;
    }

    public HPPError(Throwable throwable, String url) {
        super(throwable);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
