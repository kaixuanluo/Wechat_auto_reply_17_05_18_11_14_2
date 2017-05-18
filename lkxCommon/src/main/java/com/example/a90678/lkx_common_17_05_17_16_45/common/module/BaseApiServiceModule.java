package com.example.a90678.lkx_common_17_05_17_16_45.common.module;

import com.example.a90678.lkx_common_17_05_17_16_45.common.data.BaseApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arron on 16/7/26.
 */
@Module
public class BaseApiServiceModule {

//    public static final String API_SERVER_URL = "http://192.168.1.34:5555";
//public static final String API_SERVER_URL = "http://192.168.1.31:85";
//public static final String API_SERVER_URL = "http://192.168.1.10";
public static final String API_SERVER_URL = "http://oa.isoffice.cn/";

    private OkHttpClient.Builder mBuilder;
    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

//    @Provides
//    @Singleton
//    protected OkHttpClient provideClient() {
//        return new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS)
//                .readTimeout(5000, TimeUnit.MILLISECONDS).addInterceptor(new HttpLoggingInterceptor().setLevel(
//                        HttpLoggingInterceptor.Level.BODY)).build();
//    }

    @Provides
    @Singleton
    protected RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    protected GsonConverterFactory provideGsonConverterFactory() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    protected OkHttpClient.Builder provideOkHttpBuilder() {
        if (mBuilder == null) {
            mBuilder = new OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS).addInterceptor(new HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY))
                    .cookieJar(new CookieJar() {
                        //MD,終於把Cookie帶過去了，這裡key必須是String啊！！！！！！！！！！！！

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url.host(), cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(url.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    });

            return mBuilder;

        } else {
            return mBuilder;
        }
    }

    @Provides
    @Singleton
    protected OkHttpClient provideClient(OkHttpClient.Builder builder) {
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    protected Retrofit.Builder provideRetrofitBuilder(RxJavaCallAdapterFactory rxJavaCallAdapterFactory,
                                                      GsonConverterFactory gsonConverterFactory, OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .baseUrl(API_SERVER_URL);
    }

    @Provides
    @Singleton
    protected Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    protected BaseApiService provideApiService(Retrofit build) {
        return build.create(BaseApiService.class);
    }

//    @Provides
//    @Singleton
//    protected LoginApiService provideLoginService(Retrofit build) {
//        return build.create(LoginApiService.class);
//    }

//    @Provides
//    @Singleton
//    protected MailApiService provideEmailService(Retrofit build) {
//        return build.create(MailApiService.class);
//    }

}
