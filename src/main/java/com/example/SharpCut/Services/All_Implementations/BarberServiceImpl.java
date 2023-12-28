package com.example.SharpCut.Services.All_Implementations;

import com.example.SharpCut.Entities.Barber;
import com.example.SharpCut.Entities.BarberRating;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.BarberRequest;
import com.example.SharpCut.Models.Response.BarberResponse;
import com.example.SharpCut.Repositories.BarberRepository;
import com.example.SharpCut.Services.All_Services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarberServiceImpl implements BarberService {
    @Autowired
    private BarberRepository barberRepository;

    public BarberResponse Barber_To_BarberResponse(Barber barber) {
        BarberResponse barberResponse = new BarberResponse();
        barberResponse.setId(barber.getId());
        barberResponse.setBarberName(barber.getBarberName());
        barberResponse.setBio(barber.getBio());
        barberResponse.setImgPublicId(barber.getImgPublicId());
        barberResponse.setImgUrl(barber.getImgUrl());
        Float rating = 0F;
        for (int i = 0; i < barber.getBarberRatingList().size(); i++) {
            rating += (float) barber.getBarberRatingList().get(i).getRating();
        }
        if (barber.getBarberRatingList().size() > 0) {
            barberResponse.setBarberRating((float) (rating/barber.getBarberRatingList().size()));
        } else {
            barberResponse.setBarberRating(0F);
        }
        return barberResponse;
    }

    @Override
    public Barber insertBarber(Barber barber) {
        return this.barberRepository.save(barber);
    }

    @Override
    public Barber insertBarber(BarberRequest barberRequest) {
        Barber barber = new Barber();
        barber.setBarberName(barberRequest.getName());
        barber.setBio(barberRequest.getBio());
        return this.insertBarber(barber);
    }

    @Override
    public Barber updateBarber(Integer barberId, Barber barber) throws ResourceNotFoundException {
        Barber barber1 = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        barber1.setBarberName(barber.getBarberName());
        barber1.setBio(barber.getBio());
        return this.barberRepository.save(barber1);
    }

    @Override
    public Boolean deleteBarber(Integer barberId) throws ResourceNotFoundException {
        Barber barber = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        this.barberRepository.deleteById(barberId);
        return true;
    }

    @Override
    public List<Barber> getAll() {
        return  this.barberRepository.findAll();
    }

    @Override
    public Barber getOneById(Integer barberId) throws ResourceNotFoundException {
        Barber barber = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        return barber;
    }

    @Override
    public Barber rateBarber(Integer barberId, Integer rating) throws ResourceNotFoundException {
        Barber barber = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        BarberRating barberRating = new BarberRating();
        barberRating.setRating(rating);
        barber.addBarberRating(barberRating);
        return this.barberRepository.save(barber);
    }







    @Override
    public BarberResponse insertBarber_Response(Barber barber) {
        return this.Barber_To_BarberResponse(this.barberRepository.save(barber));
    }

    @Override
    public BarberResponse insertBarber_Response(BarberRequest barberRequest) {
        Barber barber = new Barber();
        barber.setBarberName(barberRequest.getName());
        barber.setBio(barberRequest.getBio());
        return this.Barber_To_BarberResponse(this.insertBarber(barber));
    }

    @Override
    public BarberResponse updateBarber_Response(Integer barberId, Barber barber) throws ResourceNotFoundException {
        Barber barber1 = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        barber1.setBarberName(barber.getBarberName());
        barber1.setBio(barber.getBio());
        return this.Barber_To_BarberResponse(this.barberRepository.save(barber1));
    }

    @Override
    public List<BarberResponse> getAll_Response() {
        List<Barber> barberList = this.barberRepository.findAll();
        List<BarberResponse> barberResponseList = new ArrayList<>();
        for (int i = 0; i < barberList.size(); i++) {
            barberResponseList.add(this.Barber_To_BarberResponse(barberList.get(i)));
        }
        return barberResponseList;
    }

    @Override
    public BarberResponse getOneById_Response(Integer barberId) throws ResourceNotFoundException {
        Barber barber = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        return this.Barber_To_BarberResponse(barber);
    }

    @Override
    public BarberResponse rateBarber_Response(Integer barberId, Integer rating) throws ResourceNotFoundException {
        Barber barber = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber","does not Found!"));
        BarberRating barberRating = new BarberRating();
        barberRating.setRating(rating);
        barber.addBarberRating(barberRating);
        return this.Barber_To_BarberResponse(this.barberRepository.save(barber));
    }
}
