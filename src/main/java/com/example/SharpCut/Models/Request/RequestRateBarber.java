package com.example.SharpCut.Models.Request;

public class RequestRateBarber {
    Integer rating;

    public RequestRateBarber() {
        super();
    }

    public RequestRateBarber(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
