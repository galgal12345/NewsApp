package com.drillgil.newsapp;


public class Article {

    private String mWebTitle;
    private String mSectionName;
    private String mAuthor;
    private String mWebPublicationDate;
    private String mWebUrl;

    public Article(String mWebTitle, String mSectionName, String mAuthor, String mWebPublicationDate, String mWebUrl) {
        this.mWebTitle = mWebTitle;
        this.mSectionName = mSectionName;
        this.mAuthor = mAuthor;
        this.mWebPublicationDate = mWebPublicationDate;
        this.mWebUrl = mWebUrl;
    }

    public String getmWebTitle() {
        return mWebTitle;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }
}
