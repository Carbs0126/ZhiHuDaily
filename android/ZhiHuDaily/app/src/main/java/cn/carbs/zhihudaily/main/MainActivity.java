package cn.carbs.zhihudaily.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.carbs.zhihudaily.R;
import cn.carbs.zhihudaily.main.adapter.AdapterNews;
import cn.carbs.zhihudaily.net.ZhiHuHttps;
import cn.carbs.zhihudaily.net.entity.NewsLatest;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private View v_title;
    private ImageView iv_menu;
    private TextView tv_title;
    private RecyclerView recycler_view;
    private AdapterNews adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView(){
        setContentView(R.layout.activity_main);
        v_title = findViewById(R.id.v_title);
        iv_menu = (ImageView)findViewById(R.id.iv_menu);
        tv_title = (TextView)findViewById(R.id.tv_title);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(this);
        }
        recycler_view.setLayoutManager(linearLayoutManager);
//        recycler_view.clearOnScrollListeners();
    }

    private void initData(){
        ZhiHuHttps.getInstance().getLatestNews(getLatestNewsSubscriber());
    }

    private Subscriber getLatestNewsSubscriber() {
        return new Subscriber<NewsLatest>() {
            public void onCompleted() {
            }
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            public void onNext(NewsLatest json) {
                if (json != null && !TextUtils.isEmpty(json.date)
                        && json.stories != null){
                    showData(json);
                }
            }
        };
    }

    private void showData(NewsLatest json){
        if(adapter == null) {
            adapter = new AdapterNews(this, json.stories);
            recycler_view.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }
}
