package com.newsarticle.UI.Home;

import com.newsarticle.UI.Home.Model.ArticleModel;

/**
 * Created by akhil on 7/10/18.
 */

public interface HomeListner {
    void showProgress(String msg);
    void hideProgress();
    void onResponseSuccess(ArticleModel articleModel);
    void onResponseFailure(String msg);
    void retrofitError(String msg);
}
