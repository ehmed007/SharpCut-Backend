package com.example.SharpCut.Exceptions.CustomExceptions;

public class AppointmentDateNotValid extends Exception{
    private String resourceName;
    private String message;

    public AppointmentDateNotValid() {
        super();
    }

    public AppointmentDateNotValid(String resourceName, String message) {
        this.resourceName = resourceName;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.resourceName + " " + message;
    }
}
