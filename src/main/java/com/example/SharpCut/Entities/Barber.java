package com.example.SharpCut.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String barberName;
    private String bio;
    private String imgUrl;
    private String imgPublicId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "barber_id",referencedColumnName = "id")
    private List<BarberRating> barberRatingList;

    public Barber() {
        super();
    }

    public Barber(Integer id, String barberName, String bio, String imgUrl, String imgPublicId, List<BarberRating> barberRatingList) {
        this.id = id;
        this.barberName = barberName;
        this.bio = bio;
        this.imgUrl = imgUrl;
        this.imgPublicId = imgPublicId;
        this.barberRatingList = barberRatingList;
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

    public List<BarberRating> getBarberRatingList() {
        return barberRatingList;
    }

    public void setBarberRatingList(List<BarberRating> barberRatingList) {
        this.barberRatingList = barberRatingList;
    }

    public void addBarberRating(BarberRating barberRating) {
        if (this.barberRatingList == null) {
            this.barberRatingList = new ArrayList<>();
            this.barberRatingList.add(barberRating);
            return;
        }
        this.barberRatingList.add(barberRating);
    }
}
