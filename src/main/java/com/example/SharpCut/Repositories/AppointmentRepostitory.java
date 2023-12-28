package com.example.SharpCut.Repositories;

import com.example.SharpCut.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AppointmentRepostitory extends JpaRepository<Appointment, Integer> {
    @Query(nativeQuery = true, value = "Select * from appointment where appointment.appointment_time = ?1 and appointment.slot_time = ?2 and appointment.barber_id = ?3")
    public List<Appointment> checkAvailability(String appointmentTime, Date date, Integer barberId, Integer serviceId);

}
