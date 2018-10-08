package com.newsarticle.UI.Home;

/**
 * Created by akhil on 7/10/18.
 */

public interface HomeInteractor {
    void getArticleDetails(String section,int period,String key,HomeListner homeListner);
}
