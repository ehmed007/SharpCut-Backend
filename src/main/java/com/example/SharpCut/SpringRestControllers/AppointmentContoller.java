package com.example.SharpCut.SpringRestControllers;

import com.example.SharpCut.Entities.Appointment;
import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Exceptions.CustomExceptions.AppointmentDateNotValid;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.AppointmentRequest;
import com.example.SharpCut.Services.All_Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentContoller {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/addAppointment/{serviceId}/{barberId}")
    public ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentRequest appointmentRequest, @PathVariable Integer serviceId, @PathVariable Integer barberId) throws ResourceNotFoundException, AppointmentDateNotValid {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Appointment appointment = this.appointmentService.insertAppointment(appointmentRequest, profile.getId(), barberId, serviceId);
        return new ResponseEntity<>(appointment, HttpStatusCode.valueOf(200));
    }


    @PostMapping("/checkAppointment/{serviceId}/{barberId}")
    public ResponseEntity<Map> checkAppointment(@RequestBody AppointmentRequest appointmentRequest, @PathVariable Integer barberId, @PathVariable Integer serviceId) {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map map = new HashMap();
        map.put("message",this.appointmentService.checkAvailability(appointmentRequest.getAppointmentTime(), appointmentRequest.getSlotTime(), profile.getId(), barberId, serviceId));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }
    
}
