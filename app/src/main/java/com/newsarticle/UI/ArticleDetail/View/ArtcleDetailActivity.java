package com.newsarticle.UI.ArticleDetail.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsarticle.R;
import com.newsarticle.UI.Home.Model.MediaMetadata;
import com.newsarticle.UI.Home.Model.Results;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akhil on 8/10/18.
 */

public class ArtcleDetailActivity extends AppCompatActivity{
    @BindView(R.id.bannerImageView)
    ImageView bannerImageView;
    @BindView(R.id.backIcon)
    ImageView backIcon;
    @BindView(R.id.contentTitleTextView)
    TextView contentTitleTextView;
    @BindView(R.id.contentBylineTextView)
    TextView contentBylineTextView;
    @BindView(R.id.contentpublishDateTextView)
    TextView contentpublishDateTextView;
    @BindView(R.id.contentSourceTextView)
    TextView contentSourceTextView;
    @BindView(R.id.contentAbstractTextView)
    TextView contentAbstractTextView;
    Results results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);
        if(getIntent().getSerializableExtra("RESULT") != null){
            results = (Results) getIntent().getSerializableExtra("RESULT");
            setData();
            for (MediaMetadata mediaMetadatum:results.getMedia().get(0).getMediaMetadata()){
                if(mediaMetadatum.getFormat().equalsIgnoreCase("square640")){
                    Picasso.with(this).load(mediaMetadatum.getUrl()).fit().centerCrop().
                            into(bannerImageView);
                    break;
                }
            }
        }
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setData(){
        contentTitleTextView.setText(results.getTitle());
        contentBylineTextView.setText(results.getByline());
        contentpublishDateTextView.setText(results.getPublished_date());
        contentSourceTextView.setText(results.getSource());
    }
}
