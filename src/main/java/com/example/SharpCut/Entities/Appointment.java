package com.example.SharpCut.Entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String appointmentTime;
    private Date slotTime;

    @ManyToOne
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "barber_id",referencedColumnName = "id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "service_id",referencedColumnName = "id")
    private Service service;

    public Appointment() {
        super();
    }

    public Appointment(Integer id, String appointmentTime, Date slotTime, Profile profile, Barber barber, Service service) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.slotTime = slotTime;
        this.profile = profile;
        this.barber = barber;
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Date getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(Date slotTime) {
        this.slotTime = slotTime;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
