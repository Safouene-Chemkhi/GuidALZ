package com.groupe36.supcom.guidalz_10;

/**
 * Created by safouene on 12/11/2017.
 */

public class Rdv {
    String location;
    String date ;
    String hour ;

    public Rdv() {
    }

    public Rdv(String location, String date, String hour) {
        this.location = location;
        this.date = date;
        this.hour = hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
