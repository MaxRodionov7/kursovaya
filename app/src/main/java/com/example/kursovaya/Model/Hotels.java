package com.example.kursovaya.Model;

public class Hotels {
    private String hname, description, price, image, country, hid, date, time;

    public Hotels(){

    }

    public Hotels(String hname, String description, String price, String image, String country, String hid, String date, String time) {
        this.hname = hname;
        this.description = description;
        this.price = price;
        this.image = image;
        this.country = country;
        this.hid = hid;
        this.date = date;
        this.time = time;
    }

    public String getHname() {
        return hname;
    }

    public void setPname(String hname) {
        this.hname = hname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCategory(String category) {
        this.country = country;
    }

    public String getPid() {
        return hid;
    }

    public void setPid(String pid) {
        this.hid = hid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
