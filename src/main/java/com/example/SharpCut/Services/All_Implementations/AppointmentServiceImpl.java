package com.example.SharpCut.Services.All_Implementations;

import com.example.SharpCut.Entities.Appointment;
import com.example.SharpCut.Entities.Barber;
import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Entities.Service;
import com.example.SharpCut.Exceptions.CustomExceptions.AppointmentDateNotValid;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.AppointmentRequest;
import com.example.SharpCut.Repositories.AppointmentRepostitory;
import com.example.SharpCut.Repositories.BarberRepository;
import com.example.SharpCut.Repositories.ProfileRepository;
import com.example.SharpCut.Repositories.ServiceRepository;
import com.example.SharpCut.Services.All_Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepostitory appointmentRepostitory;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public synchronized Appointment insertAppointment(Appointment appointment, Integer profileId, Integer barberId, Integer serviceId) throws ResourceNotFoundException {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("profile "+profileId.toString(),"does not exist!"));
        Barber barber = barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("barber "+barberId.toString(),"does not exist!"));
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("service"+serviceId.toString(),"does not exist!"));

        appointment.setProfile(profile);
        appointment.setBarber(barber);
        appointment.setService(service);

        return this.appointmentRepostitory.save(appointment);
    }

    @Override
    public synchronized Appointment insertAppointment(AppointmentRequest appointmentRequest, Integer profileId, Integer barberId, Integer serviceId) throws ResourceNotFoundException, AppointmentDateNotValid {

        if (appointmentRequest.getSlotTime().before(new java.util.Date())) {
            throw new AppointmentDateNotValid("Appointment Date"," should be valid(should be onward from today)");
        }

        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("profile "+profileId.toString(),"does not exist!"));
        Barber barber = barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("barber "+barberId.toString(),"does not exist!"));
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("service"+serviceId.toString(),"does not exist!"));

        Appointment appointment = new Appointment();
        appointment.setSlotTime(appointmentRequest.getSlotTime());
        appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());
        appointment.setProfile(profile);
        appointment.setBarber(barber);
        appointment.setService(service);

        return this.appointmentRepostitory.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Integer appointmentId, Appointment appointment) throws ResourceNotFoundException {
        Appointment appointment1 = this.appointmentRepostitory.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("appointment"+appointmentId,"does not exist!"));
        appointment1.setAppointmentTime(appointment.getAppointmentTime());
        appointment1.setSlotTime(appointment.getSlotTime());
        return this.appointmentRepostitory.save(appointment1);
    }

    @Override
    public Boolean deleteAppointment(Integer appointmentId) throws ResourceNotFoundException {
        Appointment appointment1 = this.appointmentRepostitory.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("appointment"+appointmentId,"does not exist!"));
        this.appointmentRepostitory.deleteById(appointmentId);
        return true;
    }

    @Override
    public List<Appointment> getAll() {
        return this.appointmentRepostitory.findAll();
    }

    @Override
    public Appointment getOneById(Integer appointmentId) throws ResourceNotFoundException {
        return this.appointmentRepostitory.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("appointment"+appointmentId,"does not exist!"));
    }

    @Override
    public synchronized boolean checkAvailability(String appointmentTime, Date date, Integer profileId, Integer barberId, Integer serviceId) {
        List<Appointment> appointmentList = this.appointmentRepostitory.checkAvailability(appointmentTime, date, barberId, serviceId);
        if (appointmentList.isEmpty()) {
            return true;
        }
        return false;
    }
}
