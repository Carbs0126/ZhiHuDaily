package cn.carbs.zhihudaily.net.api;

import cn.carbs.zhihudaily.constant.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andy.Wang on 2016/3/9.
 */
public class ApiFactory {

    private Api api;

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static <T> T createApiWithoutAuth(Class<T> clazz, String url) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
            OkHttpClient okHttpLogClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            builder.client(okHttpLogClient);
        Retrofit retrofit = builder
                .build();
        return retrofit.create(clazz);
    }


    private static class SingletonHolder {
        private static final ApiFactory INSTANCE = new ApiFactory();
    }

    public static ApiFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ApiFactory() {
        api = createApiWithoutAuth(Api.class, Constants.NetConfig.BaseUrl);
    }

    public Api getEbtAPI() {
        return api;
    }

}
