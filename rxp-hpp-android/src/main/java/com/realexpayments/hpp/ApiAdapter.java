package com.realexpayments.hpp;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Helper class for server communication
 */
class ApiAdapter {

    public static final String RETROFIT_TAG = "HPPRetrofit";

    public static IHPPServerAPI getAdapter(String endpoint) {
        return getAdapter(endpoint,null);
    }

    public static IHPPServerAPI getAdapter(String endpoint,HashMap<String, String> headers) {

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(RETROFIT_TAG))
                .setConverter(new GsonConverter(getGson()));

        if (headers != null && headers.size() != 0) {
                HPPRequestInterceptor myInterceptor = new HPPRequestInterceptor(headers);
                builder.setRequestInterceptor(myInterceptor);
        }

        RestAdapter restAdapter = builder.build();

        return restAdapter.create(IHPPServerAPI.class);
    }

    public static Gson getGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }
}
