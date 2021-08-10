package com.realexpayments.hpp;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Helper class for server communication
 */
class ApiAdapter {

    public static final String RETROFIT_TAG = "HPPRetrofit";

    public static IHPPServerAPI getAdapter(String endpoint, Map<String, String> headers) {
        RestAdapter.Builder builderRestAdapter =
                new RestAdapter
                        .Builder()
                        .setEndpoint(endpoint)
                        .setConverter(new GsonConverter(getGson()))
                        .setRequestInterceptor(getRequestInterceptor(headers));
        if (BuildConfig.DEBUG) {
            builderRestAdapter
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setLog(new AndroidLog(RETROFIT_TAG));
        }
        RestAdapter restAdapter = builderRestAdapter.build();
        return restAdapter.create(IHPPServerAPI.class);
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private static RequestInterceptor getRequestInterceptor(final Map<String, String> headers) {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                for (String headerName : headers.keySet()) {
                    String headerValue = headers.get(headerName);
                    request.addHeader(headerName, headerValue);
                }
            }
        };
    }
}
