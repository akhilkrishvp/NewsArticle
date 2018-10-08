package com.newsarticle.UI.Home;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newsarticle.UI.Home.Model.ArticleModel;
import com.newsarticle.Webservice.RetroClientService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by akhil on 7/10/18.
 */

public class HomeInteractorImpl implements HomeInteractor {
    @Override
    public void getArticleDetails(String section, int period, String key, final HomeListner homeListner) {
        RetroClientService.getService().getArticleDetails(section,period,key).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body() != null){
                    Gson gson = new Gson();
                    ArticleModel articleModel = new ArticleModel();
                    articleModel = gson.fromJson(response.body(),ArticleModel.class);
                    homeListner.onResponseSuccess(articleModel);
                }
                else {
                    try {
                        JSONObject responseObject = new JSONObject(response.errorBody().string());
                        homeListner.onResponseFailure(responseObject.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
