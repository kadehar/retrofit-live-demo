package com.github.kadehar.demo.client;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class ServiceGenerator {
    private static final String BASE_URL = "http://dummy.restapiexample.com/";

    private static final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static <S> S createService(Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, final String token) {
        if (token != null) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder().header("Authorization", "Bearer " + token);
                Request request = builder.build();
                return chain.proceed(request);
            });
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}
