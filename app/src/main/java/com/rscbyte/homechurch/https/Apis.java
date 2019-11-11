package com.rscbyte.homechurch.https;

import com.rscbyte.homechurch.models.MProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apis {

    //define API BASE url
    public String BASE_URL = "http://jsonplaceholder.typicode.com/";

    //User login caller
    @GET("todos/1")
    Call<MProfile> postLogin();
}
