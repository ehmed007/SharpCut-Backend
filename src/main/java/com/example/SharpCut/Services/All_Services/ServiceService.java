package com.example.SharpCut.Services.All_Services;

import com.example.SharpCut.Entities.Service;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.ServiceRequest;

import java.util.List;

public interface ServiceService {
    public Service insertService(Service service);
    public Service insertService(ServiceRequest serviceRequest);
    public Service updateService(Integer serviceId, Service service) throws ResourceNotFoundException;
    public Boolean deleteService(Integer serviceId) throws ResourceNotFoundException;
    public List<Service> getAll();
    public Service getOneById(Integer serviceId) throws ResourceNotFoundException;
}
