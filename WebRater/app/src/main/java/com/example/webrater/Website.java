package com.example.webrater;

public class Website {

    String url;
    int rating;

    public Website(String url, int rating){
        this.url = url;
        this.rating = rating;
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
