package com.rscbyte.homechurch.https;

import com.rscbyte.homechurch.models.MProfile;
import com.rscbyte.homechurch.https.Listeners;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    //controller for https / api
    private Retrofit retrofit;
    private Apis apis;

    public ApiController() {
        this.retrofit = new Retrofit
                .Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apis = retrofit.create(Apis.class);
    }

    //function login
    public void userLogin(final Listeners.IAccount iAccount) {
        apis.postLogin().enqueue(new Callback<MProfile>() {
            @Override
            public void onResponse(Call<MProfile> call, Response<MProfile> response) {
                iAccount.isLogin(response.body().getNames(), true);
            }

            @Override
            public void onFailure(Call<MProfile> call, Throwable t) {
                iAccount.isLogin(null, false);
            }
        });
    }
}
