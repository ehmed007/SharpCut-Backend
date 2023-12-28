package com.example.SharpCut.Models.Request;

import java.sql.Date;

public class AppointmentRequest {
    private String appointmentTime;
    private Date slotTime;

    public AppointmentRequest() {
        super();
    }

    public AppointmentRequest(String appointmentTime, Date slotTime) {
        this.appointmentTime = appointmentTime;
        this.slotTime = slotTime;
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
}
