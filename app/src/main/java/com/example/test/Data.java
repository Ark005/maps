package com.example.test;

public class Data {
    private String sensor;
    private double time;
    private double x;
    private double y;
    private double z;



    public Data (String sensor,double time,double x,double y,double z){
        this.sensor=sensor;
        this.time=time;
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public String getSensor(){
        return sensor;
    }
    public double getTime(){
        return time;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
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
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }
    public void setZ(double z){
        this.z=z;
    }
}
