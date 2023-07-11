package com.example.nfrontliner;

public class userhelper {
    String Name,Age,Bg,Email,Ph,Latitude,Longitude,Address,Em_num;

    public userhelper() {
    }

    public userhelper(String name, String age, String bg, String email, String ph, String latitude, String longitude, String address, String em_num) {
        Name = name;
        Age = age;
        Bg = bg;
        Email = email;
        Ph = ph;
        Latitude = latitude;
        Longitude = longitude;
        Address = address;
        Em_num = em_num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBg() {
        return Bg;
    }

    public void setBg(String bg) {
        Bg = bg;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPh() {
        return Ph;
    }

    public void setPh(String ph) {
        Ph = ph;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEm_num() {
        return Em_num;
    }

    public void setEm_num(String em_num) {
        Em_num = em_num;
    }
}
