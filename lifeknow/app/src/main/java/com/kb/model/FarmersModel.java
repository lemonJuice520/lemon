package com.kb.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FarmersModel{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int image;//店铺图片
    private String stroename;//店铺名称
    private  String ra_grade;//评分图片显示
    private String grade;//评分
    private String stroetype;//店铺类型
    private String servertime;//服务时间
    private String address;//地址

    public String getDistanes() {
        return distanes;
    }

    public void setDistanes(String distanes) {
        this.distanes = distanes;
    }

    private String distanes;

    public String getStroetype() {
        return stroetype;
    }

    public void setStroetype(String stroetype) {
        this.stroetype = stroetype;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStroename() {
        return stroename;
    }

    public void setStroename(String stroename) {
        this.stroename = stroename;
    }

    public String getRa_grade() {
        return ra_grade;
    }

    public void setRa_grade(String ra_grade) {
        this.ra_grade = ra_grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }


}
