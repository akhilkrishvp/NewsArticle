package com.newsarticle.Webservice;

import com.newsarticle.Helper.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Foctr5 on 30-10-2017.
 */

public class RetroClientService {


    private static ApiInterface apiInterface;
    private static class HeaderInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .header("User-Agent", "Android")
                    .build();
            okhttp3.Response response = chain.proceed(request);
            return response;
        }
    }

    public static void configService(String endpoint) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint).client(client).addConverterFactory(GsonConverterFactory.create())
                .build();

        setService(retrofit.create(ApiInterface.class));
    }

    public static void setService(ApiInterface service) {
        apiInterface = service;
    }

    public static ApiInterface getService() {
        if (apiInterface == null) {
            configService(Constants.BASE_URL);
        }
        return apiInterface;
    }

}

