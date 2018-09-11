package com.example.apoorva.newspart2;

public class News {
    private String mWebUrl;
    private String mWebTitle;
    private String mAuthor;
    private String mSectionName;
    private String mDateAndTime;

    public News (String webUrl,String webTitle,String author,String sectionName,String dateAndTime)
    {
        mWebUrl = webUrl;
        mWebTitle = webTitle;
        mAuthor = author;
        mSectionName = sectionName;
        mDateAndTime = dateAndTime;
    }

    public String getWebUrl() { return mWebUrl; }
    public String getWebTitle() { return mWebTitle; }
    public String getAuthor() { return mAuthor; }
    public String getSectionName() { return mSectionName; }
    public String getDateAndTime() { return mDateAndTime; }

}

