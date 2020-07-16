package com.example.waitingroom;

import java.util.ArrayList;

public class Appointment {
    public String id;
    public String user;
    public int doctor;
    public String apointment_date;

    public Appointment(String id, String user, int doctor, String apointment_date) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.apointment_date = apointment_date;
    }

    public Appointment() {
    }

    public boolean isInDatabase(ArrayList<Appointment> allAppointments){
        Appointment appointment;
        for (int i = 0; i < allAppointments.size(); i++){
            appointment = allAppointments.get(i);
            if(appointment.id == this.id){
                return true;
            }
        }
        return false;
    }
}
