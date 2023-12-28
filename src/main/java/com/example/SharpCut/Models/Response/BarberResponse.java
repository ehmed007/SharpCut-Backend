package com.example.SharpCut.Models.Response;

public class BarberResponse {
    private Integer id;
    private String barberName;
    private String bio;
    private String imgUrl;
    private String imgPublicId;
    private Float barberRating;

    public BarberResponse() {
        super();
    }

    public BarberResponse(Integer id, String barberName, String bio, String imgUrl, String imgPublicId, Float barberRating) {
        this.id = id;
        this.barberName = barberName;
        this.bio = bio;
        this.imgUrl = imgUrl;
        this.imgPublicId = imgPublicId;
        this.barberRating = barberRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgPublicId() {
        return imgPublicId;
    }

    public void setImgPublicId(String imgPublicId) {
        this.imgPublicId = imgPublicId;
    }

    public Float getBarberRating() {
        return barberRating;
    }

    public void setBarberRating(Float barberRating) {
        this.barberRating = barberRating;
    }
}
