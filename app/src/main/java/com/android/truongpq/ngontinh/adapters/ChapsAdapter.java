package com.android.truongpq.ngontinh.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.truongpq.ngontinh.R;
import com.android.truongpq.ngontinh.StoryActivity;
import com.android.truongpq.ngontinh.models.Chap;

import java.util.List;

/**
 * Created by truongpq on 03/12/2015.
 */
public class ChapsAdapter extends RecyclerView.Adapter<ChapsAdapter.ViewHolder>{
    private List<Chap> chaps;

    public ChapsAdapter(List<Chap> chaps) {
        this.chaps = chaps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View chapView = inflater.inflate(R.layout.item_chap, parent, false);
        ViewHolder viewHolder = new ViewHolder(context,chapView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChapsAdapter.ViewHolder holder, int position) {
        Chap chap = chaps.get(position);
        TextView textView = holder.chapTextView;
        textView.setText(chap.getName());
    }

    @Override
    public int getItemCount() {
        return chaps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView chapTextView;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            chapTextView = (TextView) itemView.findViewById(R.id.chap_textview);

            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, StoryActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, chaps.get(getLayoutPosition()).getHref());
            context.startActivity(intent);
        }
    }
}
