package com.kb.model;


public class ViewPagerModel {
    private int iconResId;
    private String intro;

    public ViewPagerModel(int iconResId, String intro) {
        super();
        this.iconResId = iconResId;
        this.intro = intro;
    }
    public int getIconResId() {
        return iconResId;
    }
    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }



}
