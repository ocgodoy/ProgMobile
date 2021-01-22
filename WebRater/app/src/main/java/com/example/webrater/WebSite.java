package com.example.webrater;

public class WebSite {

    String url;
    int rating;

    public WebSite ( ) {
    }

    public String getUrl ( ) {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public int getRating ( ) {
        return rating;
    }

    public void setRating (int rating) {
        this.rating = rating;
    }
}
