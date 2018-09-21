package com.groupe36.supcom.guidalz_10;

/**
 * Created by safouene on 12/10/2017.
 */


public class HomeCoord {
    private float lattitude;
    private float longitude;

    public HomeCoord() {
    }

    public HomeCoord(float lattitude, float longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
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
}
