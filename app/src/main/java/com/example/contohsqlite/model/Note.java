package com.example.contohsqlite.model;

import android.text.TextUtils;

public class Note implements INote {

    private String Jcatatan, Kategori;
    public Note(String Jcatatan, String Kategori){
        this.Jcatatan = Jcatatan;
        this.Kategori = Kategori;
    }

    @Override
    public String getJCatatan() {
        return Jcatatan;
    }

    @Override
    public String getKategori() {
        return Kategori;
    }

    @Override
    public int isValidData() {
        if(TextUtils.isEmpty(getJCatatan()))
            return 0;
        else if(TextUtils.isEmpty(getKategori()))
            return 1;
        else
            return -1;
    }
}
