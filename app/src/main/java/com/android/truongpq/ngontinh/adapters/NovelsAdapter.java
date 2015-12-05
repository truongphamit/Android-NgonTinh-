package com.android.truongpq.ngontinh.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.truongpq.ngontinh.NovelActivity;
import com.android.truongpq.ngontinh.R;
import com.android.truongpq.ngontinh.models.Novel;
import com.android.truongpq.ngontinh.networks.JsoupImage;

import java.util.List;

/**
 * Created by truongpq on 29/11/2015.
 */
public class NovelsAdapter extends RecyclerView.Adapter<NovelsAdapter.ViewHolder> {

    private List<Novel> mNovels;

    public NovelsAdapter(List<Novel> mNovels) {
        this.mNovels = mNovels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View novelView = inflater.inflate(R.layout.item_novel, parent, false);
        ViewHolder viewHolder = new ViewHolder(context,novelView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Novel novel = mNovels.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(novel.getName());
        ImageView imageView = holder.novelImage;
        new JsoupImage(novel.getThumbnailUrl(), imageView);
    }

    @Override
    public int getItemCount() {
        return mNovels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public ImageView novelImage;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.novel_name);
            novelImage = (ImageView) itemView.findViewById(R.id.novel_image);

            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NovelActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, getLayoutPosition()+"");
            context.startActivity(intent);
        }
    }

}
