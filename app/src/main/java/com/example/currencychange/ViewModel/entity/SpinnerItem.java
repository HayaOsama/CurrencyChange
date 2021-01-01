package com.example.currencychange.ViewModel.entity;

import android.graphics.Bitmap;


public class SpinnerItem {
    private long id ;
    private Bitmap flag ;
    private String iso ;

    public SpinnerItem(long id, Bitmap flag, String iso) {
        this.id = id;
        this.flag = flag;
        this.iso = iso;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getFlag() {
        return flag;
    }

    public void setFlag(Bitmap flag) {
        this.flag = flag;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public long getId() {
        return id ;
    }
}
