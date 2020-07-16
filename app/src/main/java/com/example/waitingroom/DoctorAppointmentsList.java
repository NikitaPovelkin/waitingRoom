package com.example.waitingroom;

public class DoctorAppointmentsList {
    public String sex;
    public String name;
    public String doctor_type;
    public String apointment_date;
    public String street;
    public String postcode;

    public DoctorAppointmentsList(String sex, String name, String doctor_type, String apointment_date, String street, String postcode) {
        this.sex = sex;
        this.name = name;
        this.doctor_type = doctor_type;
        this.apointment_date = apointment_date;
        this.street = street;
        this.postcode = postcode;
    }

    public DoctorAppointmentsList() {
    }
}



