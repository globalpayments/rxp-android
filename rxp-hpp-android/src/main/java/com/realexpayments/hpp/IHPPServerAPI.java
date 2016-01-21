package com.realexpayments.hpp;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Declaration of the server api
 */

interface IHPPServerAPI {

    @FormUrlEncoded
        @POST("/{path}")
        public void getHPPRequest(@Path(value = "path", encode = false) String path, @FieldMap HashMap<String, String> args,
                                  Callback<Response> callback);

        @FormUrlEncoded
        @POST("/{path}")
        public void getConsumerRequest(@Path(value = "path", encode = false) String path, @Field("hppResponse") String hppResponse,Callback<Response> callback);

}
