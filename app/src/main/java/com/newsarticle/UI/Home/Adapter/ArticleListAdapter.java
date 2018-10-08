package com.newsarticle.UI.Home.Adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.newsarticle.R;
import com.newsarticle.UI.Home.Model.MediaMetadata;
import com.newsarticle.UI.Home.Model.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by akhil on 7/10/18.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private Activity activity;
    private List<Results> articleList;
    private OnArticleSelect mListener;
    Results results;
    public ArticleListAdapter(Activity activity, List<Results> articleList,OnArticleSelect onArticleSelect) {
        this.activity = activity;
        this.articleList = articleList;
        mListener = onArticleSelect;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.article_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        results = articleList.get(position);
        viewHolder.articleTitleTextView.setText(results.getTitle());
        viewHolder.articleByLineTextView.setText(results.getByline());
        viewHolder.articlePublishDateTextView.setText(results.getPublished_date());
        viewHolder.articleSourceTextView.setText(results.getSource());
        for (MediaMetadata mediaMetadatum:results.getMedia().get(0).getMediaMetadata()){
            if(mediaMetadatum.getFormat().equalsIgnoreCase("square320")){
                Picasso.with(activity).load(mediaMetadatum.getUrl()).fit().centerCrop().
                        into(viewHolder.articleImageView);
                break;
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.articleImageView)
        CircleImageView articleImageView;
        @BindView(R.id.rightArrowImageView)
        ImageView rightArrowImageView;
        @BindView(R.id.articleTitleTextView)
        AppCompatTextView articleTitleTextView;
        @BindView(R.id.articleByLineTextView)
        AppCompatTextView articleByLineTextView;
        @BindView(R.id.articlePublishDateTextView)
        AppCompatTextView articlePublishDateTextView;
        @BindView(R.id.articleSourceTextView)
        AppCompatTextView articleSourceTextView;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }
    public  interface OnArticleSelect {
        void onItemClick(int position);
    }
}

