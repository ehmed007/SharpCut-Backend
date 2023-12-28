package com.example.SharpCut.Services.All_Services;

import com.example.SharpCut.Entities.Appointment;
import com.example.SharpCut.Exceptions.CustomExceptions.AppointmentDateNotValid;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.AppointmentRequest;

import java.sql.Date;
import java.util.List;

public interface AppointmentService {
    public Appointment insertAppointment(Appointment appointment, Integer profileId, Integer barberId, Integer serviceId) throws ResourceNotFoundException;
    public Appointment insertAppointment(AppointmentRequest appointmentRequest, Integer profileId, Integer barberId, Integer serviceId) throws ResourceNotFoundException, AppointmentDateNotValid;
    public Appointment updateAppointment(Integer appointmentId, Appointment appointment) throws ResourceNotFoundException;
    public Boolean deleteAppointment(Integer appointmentId) throws ResourceNotFoundException;
    public List<Appointment> getAll();
    public Appointment getOneById(Integer appointmentId) throws ResourceNotFoundException;
    public boolean checkAvailability(String appointmentTime, Date date, Integer profileId, Integer barberId, Integer serviceId);
}
