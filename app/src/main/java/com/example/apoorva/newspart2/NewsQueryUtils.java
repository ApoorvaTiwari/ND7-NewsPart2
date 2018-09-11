package com.example.apoorva.newspart2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public final class NewsQueryUtils {
    public static ArrayList<News> fetchNewsData(String requestedUrl) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL connectionUrl = createUrl(requestedUrl);

        String jsonResponse = createHttpRequest(connectionUrl);

        ArrayList<News> newsData = extractNews(jsonResponse);

        return newsData;
    }
    private static String createHttpRequest(URL url)
    {
        String jsonResponse = "";
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            jsonResponse = convertStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }
    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        try {
            while ((line = bufferedReader.readLine())!=null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    private static ArrayList<News> extractNews(String jsonResponse) {
        ArrayList<News> newsArrayList = new ArrayList<News>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray results = jsonObject.getJSONObject("response").getJSONArray("results");

            for (int i = 0; i<results.length(); i++) {
                JSONObject news = results.getJSONObject(i);
                String webUrl = news.getString("webUrl");
                String webTitle = news.getString("webTitle");
                String author = "";

                //Checking if the news article has an author
                if (news.has("tags")) {
                    if (news.getJSONArray("tags").length()>0) {
                        author = news.getJSONArray("tags").getJSONObject(0).getString("webTitle");
                    }
                }

                String sectionName = news.getString("sectionName");

                String dateAndTime = "";

                //Checking if the news article has a publication date and time
                if (news.has("webPublicationDate")) {
                    dateAndTime = news.getString("webPublicationDate");
                }

                News currentNews = new News(webUrl,webTitle,author,sectionName,dateAndTime);
                newsArrayList.add(currentNews);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsArrayList;
    }
    private static URL createUrl(String urlString) {
        URL connectedUrl = null;

        try {
            connectedUrl = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return connectedUrl;
    }
}

