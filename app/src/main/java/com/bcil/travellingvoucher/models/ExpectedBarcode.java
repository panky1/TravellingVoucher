package com.bcil.travellingvoucher.models;

/**
 * Created by NG on 01-Dec-2017.
 */

public class ExpectedBarcode {
    int id;
    String zip;
    String location;
    String zipvalue;

    public ExpectedBarcode(int id, String zip, String location){
        this.id = id;
        this.zip = zip;
        this.location = location;
    }



    public ExpectedBarcode() {

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
