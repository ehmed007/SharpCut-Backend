package com.example.SharpCut.Services.All_Services;

import com.example.SharpCut.Entities.BarberRating;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;

import java.util.List;

public interface BarberRatingService {
    public BarberRating insertBarberRating(BarberRating barberRating);
    public BarberRating updateBarberRating(Integer barberRatingId, BarberRating barberRating) throws ResourceNotFoundException;
    public Boolean deleteBarberRating(Integer barberRatingId) throws ResourceNotFoundException;
    public List<BarberRating> getAll();
    public BarberRating getOneById(Integer barberRatingId) throws ResourceNotFoundException;
}
