package com.example.online_groceries_app.retrofit;

import android.util.Log;

import com.example.online_groceries_app.util.Configs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Http {
    static Retrofit retrofit;

    public static void initialize() {
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Configs.development_baseUrl)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static <T> T create(Class<T> object) {
        if (retrofit == null) {
            initialize();
        }
        return retrofit.create(object);
    }

    private static OkHttpClient getHeader() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("InterceptorMessage ", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(
                        chain -> {
                            Request request = chain.request();
                            Request.Builder requestBuilder = request.newBuilder();
                            requestBuilder.addHeader("Content-Type", "multipart/form-data");
                            requestBuilder.addHeader("Accept", "application/json");
                            request = requestBuilder.build();
                            return chain.proceed(request);
                        }).build();
        return okClient;
    }
}
