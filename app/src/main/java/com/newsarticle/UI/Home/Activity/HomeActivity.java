package com.newsarticle.UI.Home.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.newsarticle.Helper.Constants;
import com.newsarticle.Helper.MyApplication;
import com.newsarticle.R;
import com.newsarticle.UI.ArticleDetail.View.ArtcleDetailActivity;
import com.newsarticle.UI.Home.Adapter.ArticleListAdapter;
import com.newsarticle.UI.Home.HomePresenter;
import com.newsarticle.UI.Home.HomePresenterImpl;
import com.newsarticle.UI.Home.HomeView;
import com.newsarticle.UI.Home.Model.ArticleModel;
import com.newsarticle.UI.Home.Model.Results;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akhil on 7/10/18.
 */

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeView,ArticleListAdapter.OnArticleSelect {
    @BindView(R.id.articleListRV)
    RecyclerView articleListRV;
    private MaterialSearchView searchView;
    HomePresenter homePresenter;
    Dialog mProgressDialog;
    Toolbar toolbar;
    ArticleModel articleModel;
    ArticleListAdapter listAdapter;
    List<Results> articleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        //searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                articleList.clear();
                articleList.addAll(getFilteredList(newText));
                listAdapter.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        initProgress();
        initPresenter();
        initRV();
        getArticleDetails();
    }

    public void initRV(){
        articleListRV.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false));
        listAdapter = new ArticleListAdapter(HomeActivity.this,articleList,this);
        articleListRV.setAdapter(listAdapter);
    }
    public void initSerchView(){
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
    }
    public void initMenu(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NY Times Most Popular");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void initProgress(){
        mProgressDialog = new Dialog(this);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(R.layout.progress_bar);
        mProgressDialog.getWindow().setBackgroundDrawable(null);
    }
    public void initPresenter(){
        homePresenter = new HomePresenterImpl(this);
    }
    public void getArticleDetails(){
        if(isNetworkAvailable()){
            mProgressDialog.show();
            homePresenter.getArticleDetails(Constants.SECTION,Constants.PERIOD,Constants.API_KEY);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                Log.e("serach key word",searchWrd);

            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResponseSuccess(ArticleModel articlemodel) {
        articleList.clear();
        mProgressDialog.dismiss();
        articleList.addAll(articlemodel.getResults());
        articleModel = articlemodel;
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(String msg) {
        mProgressDialog.dismiss();
    }

    @Override
    public void retrofitError(String msg) {
        mProgressDialog.dismiss();
    }
    public static Boolean isNetworkAvailable() {

        ConnectivityManager cm =
                (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    private List<Results> getFilteredList(String searchword){
        List<Results> filteredList = new ArrayList<>();
        for (Results results: articleModel.getResults()){
            if(results.getTitle().toLowerCase().contains(searchword.toLowerCase())
                    || results.getByline().toLowerCase().contains(searchword.toLowerCase())){
                filteredList.add(results);
            }
        }
        return filteredList;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), ArtcleDetailActivity.class);
        intent.putExtra("RESULT", articleList.get(position));
        startActivity(intent);
    }
}
