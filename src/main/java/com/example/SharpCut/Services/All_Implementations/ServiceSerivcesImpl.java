package com.example.SharpCut.Services.All_Implementations;

import com.example.SharpCut.Entities.Service;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.ServiceRequest;
import com.example.SharpCut.Repositories.ServiceRepository;
import com.example.SharpCut.Services.All_Services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceSerivcesImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public Service insertService(Service service) {
        return this.serviceRepository.save(service);
    }

    @Override
    public Service insertService(ServiceRequest serviceRequest) {
        Service service = new Service();
        service.setName(serviceRequest.getName());
        service.setDescription(serviceRequest.getDescription());
        service.setPrice(serviceRequest.getPrice());
        return this.insertService(service);
    }

    @Override
    public Service updateService(Integer serviceId, Service service) throws ResourceNotFoundException {
        Service service1 = this.serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service "+serviceId,"does not  exist!"));
        service1.setName(service.getName());
        service1.setDescription(service.getDescription());
        service1.setPrice(service.getPrice());
        return this.serviceRepository.save(service1);
    }

    @Override
    public Boolean deleteService(Integer serviceId) throws ResourceNotFoundException {
        Service service1 = this.serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service "+serviceId,"does not  exist!"));
        this.serviceRepository.deleteById(serviceId);
        return true;
    }

    @Override
    public List<Service> getAll() {
        return this.serviceRepository.findAll();
    }

    @Override
    public Service getOneById(Integer serviceId) throws ResourceNotFoundException {
        return this.serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service "+serviceId,"does not  exist!"));
    }
}
