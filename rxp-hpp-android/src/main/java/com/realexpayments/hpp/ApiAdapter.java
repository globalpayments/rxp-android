package com.realexpayments.hpp;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Helper class for server communication
 */
class ApiAdapter {

    public static final String RETROFIT_TAG = "HPPRetrofit";

    public static IHPPServerAPI getAdapter(String endpoint) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(RETROFIT_TAG))
                .setConverter(new GsonConverter(getGson()))
                .build();
        return restAdapter.create(IHPPServerAPI.class);
    }

    public static Gson getGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }
}
