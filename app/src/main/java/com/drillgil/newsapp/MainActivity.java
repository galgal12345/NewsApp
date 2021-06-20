package com.drillgil.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    private ArticleAdapter articleAdapter;
    private static final int ARTICLE_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    private ProgressBar progressBar;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();

        ListView articleListView = findViewById(R.id.list);
        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        articleListView.setAdapter(articleAdapter);


        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Article currentArticle = articleAdapter.getItem(position);
                Uri articleUri = Uri.parse(currentArticle.getmWebUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);
                if (websiteIntent.resolveActivity(getPackageManager()) != null) {

                    startActivity(websiteIntent); //where intent is your intent

                }

            }
        });

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);

        progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.VISIBLE);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);

    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int id, @Nullable Bundle args) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https").authority("content.guardianapis.com").appendPath("search").appendQueryParameter("show-tags", "contributor").appendQueryParameter("api-key", "test");
        String myUrl = builder.build().toString();

        return new ArticleLoader(this, myUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Article>> loader, List<Article> data) {


        if (activeNetwork != null) {
            // connected to the internet
            progressBar.setVisibility(View.GONE);
            // Set empty state text to display "No articles found."
            mEmptyStateTextView.setText(R.string.no_articles);

        } else {
            // not connected to the internet
            progressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_conection);
        }

        articleAdapter.clear();

        if (data != null && !data.isEmpty()) {
            articleAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Article>> loader) {
        articleAdapter.clear();
    }
}