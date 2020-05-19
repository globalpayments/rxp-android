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

    String PATH_ARG = "path";
    String PATH = "/{" + PATH_ARG + "}";

    @FormUrlEncoded
    @POST(PATH)
    void getHPPRequest(
            @Path(value = PATH_ARG, encode = false) String path,
            @FieldMap HashMap<String, String> args,
            Callback<Response> callback
    );

    @FormUrlEncoded
    @POST(PATH)
    void getConsumerRequest(
            @Path(value = PATH_ARG, encode = false) String path,
            @Field("hppResponse") String hppResponse,
            Callback<Response> callback
    );

    @FormUrlEncoded
    @POST(PATH)
    void getHPP(
            @Path(value = PATH_ARG, encode = false) String path,
            @FieldMap HashMap<String, String> args,
            Callback<Response> callback
    );
}
