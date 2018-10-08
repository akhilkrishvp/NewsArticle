package com.newsarticle.UI.Home;

import com.newsarticle.UI.Home.Model.ArticleModel;

/**
 * Created by akhil on 7/10/18.
 */

public class HomePresenterImpl implements HomePresenter,HomeListner{
    HomeView homeView;
    HomeInteractor homeInteractor;
    public HomePresenterImpl(HomeView homeview){
        this.homeView = homeview;
        homeInteractor = new HomeInteractorImpl();
    }
    @Override
    public void getArticleDetails(String section, int period, String key) {
        homeInteractor.getArticleDetails(section,period,key,this);
    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResponseSuccess(ArticleModel articleModel) {
        homeView.hideProgress();
        homeView.onResponseSuccess(articleModel);
    }

    @Override
    public void onResponseFailure(String msg) {
        homeView.hideProgress();
        homeView.onResponseFailure(msg);
    }

    @Override
    public void retrofitError(String msg) {
        homeView.hideProgress();
        homeView.retrofitError(msg);
    }
}
