package com.example.contohsqlite.presenter;

import com.example.contohsqlite.model.Note;
import com.example.contohsqlite.view.ISimpanView;

public class SimpanPresenter implements ISimpanPresenter {

    ISimpanView simpanView;

    public SimpanPresenter(ISimpanView simpanView){
        this.simpanView = simpanView;
    }

    @Override
    public void onSimpan(String Jcatatan, String Kategori) {
        Note note = new Note(Jcatatan, Kategori);
        int loginCode = note.isValidData();

        if(loginCode == 0)
            simpanView.onSimpanError("Judul Masih Kosong");
        else if(loginCode == 1)
            simpanView.onSimpanError("Isi Kategori");
        else
            simpanView.onSimpanSuccess("Catatan Tersimpan");
    }
}
