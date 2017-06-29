package cn.carbs.zhihudaily.main.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cn.carbs.zhihudaily.R;
import cn.carbs.zhihudaily.net.entity.Story;
import cn.carbs.zhihudaily.util.ClickManager;

/**
 * Created by Rick.Wang on 2016/4/12.
 */
public class NewsItemView extends RelativeLayout implements View.OnClickListener {

    private TextView text_view;
    private ImageView image_view;
    private View v_item;
    private Story story;

    public NewsItemView(Context context) {
        this(context, null);
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View contentView = inflate(context, R.layout.view_item_news, this);
        text_view = (TextView) contentView.findViewById(R.id.text_view);
        image_view = (ImageView) contentView.findViewById(R.id.image_view);
        v_item = contentView.findViewById(R.id.v_item);
        v_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_item:
                ClickManager.getInstance().postRunnableForRipple(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }

    public void update(Story story) {
        if (story != null) {
            this.story = story;
        }
        text_view.setText(story.title);

        Glide
                .with(getContext())
                .load(getShowImageUrl(story))
                .centerCrop()
                .crossFade()
                .into(image_view);
    }

    private String getShowImageUrl(Story story) {
        String imageUrl = null;
        if (story.images != null && story.images.size() > 0) {
            imageUrl = story.images.get(0);
        }
        return imageUrl;
    }
}