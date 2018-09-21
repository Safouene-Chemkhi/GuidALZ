package com.groupe36.supcom.guidalz_10;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by safouene on 12/6/2017.
 */

@IgnoreExtraProperties
public class User {

    public String name;
    public String fname;
    public String email;
    public String status;
    public String sex ;
    public String tel;
    public float lattitude;
    public float longitude;
    public int range;
    private String patient ;

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)


    public User() {
    }

    public User(String name, String fname ,String email, String status ,String sex , String tel) {

        this.name = name;
        this.fname = fname;
        this.email = email;
        this.sex = sex;
        this.tel = tel;
        this.status = status;
        this.lattitude=0;
        this.longitude=0;
        this.range=500;
        this.patient="default_patient";

    }
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }


    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }
// Default constructor required for calls to
    // DataSnapshot.getValue(User.class)

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {

        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
