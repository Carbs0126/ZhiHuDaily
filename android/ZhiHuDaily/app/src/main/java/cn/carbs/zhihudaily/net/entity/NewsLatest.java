package cn.carbs.zhihudaily.net.entity;

import java.util.List;

/**
 * Created by Rick.Wang on 2017/6/29.
 */
// get https://news-at.zhihu.com/api/4/news/latest
public class NewsLatest {
    public String date;
    public List<Story> stories;
}