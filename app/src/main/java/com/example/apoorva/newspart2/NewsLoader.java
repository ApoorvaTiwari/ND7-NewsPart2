package com.example.apoorva.newspart2;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mUrlString;

    public NewsLoader(Context context, String urlString) {
        super(context);
        mUrlString = urlString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        return NewsQueryUtils.fetchNewsData(mUrlString);
    }
}