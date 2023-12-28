package com.example.SharpCut.Services.All_Implementations;

import com.example.SharpCut.Entities.BarberRating;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Repositories.BarberRatingRepository;
import com.example.SharpCut.Services.All_Services.BarberRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberRatingServiceImpl implements BarberRatingService {
    @Autowired
    private BarberRatingRepository barberRatingRepository;
    @Override
    public BarberRating insertBarberRating(BarberRating barberRating) {
        return this.barberRatingRepository.save(barberRating);
    }

    @Override
    public BarberRating updateBarberRating(Integer barberRatingId, BarberRating barberRating) throws ResourceNotFoundException {
        BarberRating barberRating1 = this.barberRatingRepository.findById(barberRatingId).orElseThrow(() -> new ResourceNotFoundException("BarberRating "+barberRatingId,"does not exist!"));
        barberRating1.setRating(barberRating.getRating());
        return this.barberRatingRepository.save(barberRating1);
    }

    @Override
    public Boolean deleteBarberRating(Integer barberRatingId) throws ResourceNotFoundException {
        BarberRating barberRating1 = this.barberRatingRepository.findById(barberRatingId).orElseThrow(() -> new ResourceNotFoundException("BarberRating "+barberRatingId,"does not exist!"));
        this.barberRatingRepository.deleteById(barberRatingId);
        return true;
    }

    @Override
    public List<BarberRating> getAll() {
        return this.barberRatingRepository.findAll();
    }

    @Override
    public BarberRating getOneById(Integer barberRatingId) throws ResourceNotFoundException {
        return this.barberRatingRepository.findById(barberRatingId).orElseThrow(() -> new ResourceNotFoundException("BarberRating "+barberRatingId,"does not exist!"));
    }
}
