package com.kb.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyinfoModel {
    public   int img;
    public String number;
    public String type;

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String look;
}
