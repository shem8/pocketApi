package com.shem.pocketapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by smagnezi on 19/01/2017.
 */

public class PocketServiceImpl {

    static PocketService instance;

    public static PocketService getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://getpocket.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(PocketService.class);
        }

        return instance;
    }
}
