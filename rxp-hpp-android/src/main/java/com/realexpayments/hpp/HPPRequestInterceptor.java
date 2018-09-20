package com.realexpayments.hpp;

import java.util.HashMap;
import java.util.Set;

import retrofit.RequestInterceptor;

/**
 *  author dbures@tmb.cat
 *
 *  Sets headers to a Request
 */
public class HPPRequestInterceptor implements RequestInterceptor {

    private HashMap<String, String> headers;
    private HPPRequestInterceptor() {};

    public HPPRequestInterceptor(HashMap<String, String> headers) {

        this.headers = headers;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (String key: keys)
            {    String value;
                value = headers.get(key);
                request.addHeader(key,value);
            }
        }


    }
}
