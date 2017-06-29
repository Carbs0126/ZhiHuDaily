package cn.carbs.zhihudaily.net.api;

import cn.carbs.zhihudaily.net.entity.NewsLatest;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("api/4/news/latest")
    Observable<NewsLatest> getLatestNews();

}


