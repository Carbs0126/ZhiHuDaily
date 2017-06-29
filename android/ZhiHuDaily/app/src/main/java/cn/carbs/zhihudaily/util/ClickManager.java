package cn.carbs.zhihudaily.util;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick.Wang on 2017/1/10.
 */

public class ClickManager {

    private static long delay = 1000;
    private static final int WHAT = 1;
    private static List<Runnable> RUNNABLES = new ArrayList<>();

    private static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (RUNNABLES != null && RUNNABLES.size() > 0){
                RUNNABLES.get(0).run();
            }
            RUNNABLES.clear();
        }
    };

    private static ClickManager instance;

    private ClickManager(){}

    public static ClickManager getInstance(){
        if (instance == null) {
            synchronized (ClickManager.class) {
                if (instance == null) {
                    instance = new ClickManager();
                }
            }
        }
        return instance;
    }

    public void postDelayRunnable(Runnable r, long delay){
        if (r == null){
            return;
        }
        if (RUNNABLES == null){
            RUNNABLES = new ArrayList<>();
        }

        if (RUNNABLES.size() > 0){
            return;
        }
        RUNNABLES.add(r);
        mHandler.sendEmptyMessageDelayed(WHAT, delay);
    }

    public void postRunnableForRipple(Runnable r){
        if (r == null){
            return;
        }
        if (RUNNABLES == null){
            RUNNABLES = new ArrayList<>();
        }

        if (RUNNABLES.size() > 0){
            return;
        }
        RUNNABLES.add(r);
        mHandler.sendEmptyMessageDelayed(WHAT, android.os.Build.VERSION.SDK_INT >= 21 ? 300 : 0);
    }


}
