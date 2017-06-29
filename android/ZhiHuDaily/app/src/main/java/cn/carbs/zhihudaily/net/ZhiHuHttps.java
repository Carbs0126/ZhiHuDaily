package cn.carbs.zhihudaily.net;

import cn.carbs.zhihudaily.net.api.Api;
import cn.carbs.zhihudaily.net.api.ApiFactory;
import cn.carbs.zhihudaily.net.entity.NewsLatest;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rick.Wang on 2016/10/22.
 */
public class ZhiHuHttps {

    private Api mApi;

    private static ZhiHuHttps instance;

    private ZhiHuHttps() {
        mApi = ApiFactory.getInstance().getEbtAPI();
    }

    public static ZhiHuHttps getInstance(){
        if (instance == null) {
            synchronized (ZhiHuHttps.class) {
                if (instance == null) {
                    instance = new ZhiHuHttps();
                }
            }
        }
        return instance;
    }

    public void getLatestNews(Subscriber<NewsLatest> subscriber){
        mApi.getLatestNews()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}