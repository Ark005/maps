package com.example.test;

public class Data {
    private String sensor;
    private double time;
    private double lat;
    private double lon;
    private double z;
    private double density;


    public Data (String sensor,double time,double lat,double lon,double z, double density){
        this.sensor=sensor;
        this.time=time;
        this.lat=lat;
        this.lon=lon;
        this.z=z;
        this.density = density;
    }
    public String getSensor(){
        return sensor;
    }
    public double getTime(){
        return time;
    }
    public double getLat(){
        return lat;
    }
    public double getLon(){
        return lon;
    }
    public double getZ(){
        return z;
    }
   public void setSensor(String sensor){
        this.sensor=sensor;
    }
    public void setTime(double time){
        this.time=time;
    }
    public void setLat(double lat){
        this.lat=lat;
    }
    public void setLon(double lon){
        this.lon=lon;
    }
    public void setZ(double z){
        this.z=z;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }
}
