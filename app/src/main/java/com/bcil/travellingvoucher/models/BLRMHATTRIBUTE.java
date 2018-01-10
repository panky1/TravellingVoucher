package com.bcil.travellingvoucher.models;

/**
 * Created by NG on 06-Dec-2017.
 */

public class BLRMHATTRIBUTE {
    int id;
    String zip;
    String location;
    String zipvalue;
    public BLRMHATTRIBUTE(int id, String zip, String location,String zipvalue){
        this.id = id;
        this.zip = zip;
        this.location = location;
        this.zipvalue = zipvalue;
    }
    public BLRMHATTRIBUTE( String zip, String location,String zipvalue){
        this.zip = zip;
        this.location = location;
        this.zipvalue = zipvalue;
    }
    public BLRMHATTRIBUTE(String zip, String location){
        this.zip = zip;
        this.location = location;
    }

    public BLRMHATTRIBUTE() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZipvalue() {
        return zipvalue;
    }

    public void setZipvalue(String zipvalue) {
        this.zipvalue = zipvalue;
    }
}
