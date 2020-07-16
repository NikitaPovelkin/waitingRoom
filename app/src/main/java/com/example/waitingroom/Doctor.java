package com.example.waitingroom;


import java.util.ArrayList;

public class Doctor {
    public int id;
    public String name;
    public String doctor_type;
    public String sex;
    public String street;
    public String postcode;
    public String appointment_id;

    public Doctor() {
    }

    public Doctor(int id, String name, String doctor_type, String sex, String street, String postcode) {
        this.id = id;
        this.name = name;
        this.doctor_type = doctor_type;
        this.sex = sex;
        this.street = street;
        this.postcode = postcode;
    }

    public boolean isInDatabase(ArrayList<Doctor> allDocs){
        Doctor doc;
        for (int i = 0; i < allDocs.size(); i++){
            doc = allDocs.get(i);
            if(doc.id == this.id /* && doc.doctor_type.equals(this.doctor_type) && doc.sex.equals(this.sex) && doc.street.equals(this.street) && doc.postcode.equals(this.postcode)*/){
                return true;
            }
        }
        return false;
    }

}
