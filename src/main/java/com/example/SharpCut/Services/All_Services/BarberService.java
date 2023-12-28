package com.example.SharpCut.Services.All_Services;

import com.example.SharpCut.Entities.Barber;
import com.example.SharpCut.Entities.BarberRating;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.BarberRequest;
import com.example.SharpCut.Models.Response.BarberResponse;

import java.util.List;

public interface BarberService {
    public Barber insertBarber(Barber barber);
    public Barber insertBarber(BarberRequest barberRequest);
    public Barber updateBarber(Integer barberId, Barber barber) throws ResourceNotFoundException;
    public Boolean deleteBarber(Integer barberId) throws ResourceNotFoundException;
    public List<Barber> getAll();
    public Barber getOneById(Integer barberId) throws ResourceNotFoundException;
    public Barber rateBarber(Integer barberId, Integer rating) throws ResourceNotFoundException;



    public BarberResponse insertBarber_Response(Barber barber);
    public BarberResponse insertBarber_Response(BarberRequest barberRequest);

    public BarberResponse updateBarber_Response(Integer barberId, Barber barber) throws ResourceNotFoundException;

    public List<BarberResponse> getAll_Response();
    public BarberResponse getOneById_Response(Integer barberId) throws ResourceNotFoundException;
    public BarberResponse rateBarber_Response(Integer barberId, Integer rating) throws ResourceNotFoundException;

}
