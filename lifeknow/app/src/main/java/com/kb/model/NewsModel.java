package com.kb.model;

import android.graphics.Bitmap;

public class NewsModel{
    private String title;//资讯标题
    private int infoclass;//分类
    private int img;//图片
    private String description;//描述
    private String keywords;//关键字
    private String message;//资讯内容
    private int count ;//访问次数
    private int fcount;//收藏数
    private int rcount;//评论读数
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String time;
    Bitmap bp_img;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getInfoclass() {
        return infoclass;
    }
    public void setInfoclass(int infoclass) {
        this.infoclass = infoclass;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getFcount() {
        return fcount;
    }
    public void setFcount(int fcount) {
        this.fcount = fcount;
    }
    public int getRcount() {
        return rcount;
    }
    public void setRcount(int rcount) {
        this.rcount = rcount;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Bitmap getBp_img() {
        return bp_img;
    }
    public void setBp_img(Bitmap bp_img) {
        this.bp_img = bp_img;
    }
}
