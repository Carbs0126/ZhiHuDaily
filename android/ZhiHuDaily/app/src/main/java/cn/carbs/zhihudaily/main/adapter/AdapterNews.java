package cn.carbs.zhihudaily.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.carbs.zhihudaily.main.view.NewsItemView;
import cn.carbs.zhihudaily.net.entity.Story;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

	private List<Story> data;
	private Context context;

	public AdapterNews(Context context, List<Story> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		NewsItemView view = new NewsItemView(context);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		((NewsItemView) (viewHolder.itemView)).update(data.get(position));
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(View view) {
			super(view);
		}
	}
}
