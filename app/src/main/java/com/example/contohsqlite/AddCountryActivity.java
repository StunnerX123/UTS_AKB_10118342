package com.example.contohsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contohsqlite.presenter.ISimpanPresenter;
import com.example.contohsqlite.presenter.SimpanPresenter;

import java.text.DateFormat;
import java.util.Calendar;

public class AddCountryActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText kategoriEditText;
    private EditText descEditText;
    private TextView dateTV;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        kategoriEditText = (EditText) findViewById(R.id.kategori_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);
        dateTV = (TextView) findViewById(R.id.date_textview);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        dateTV.setText(currentDate);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = subjectEditText.getText().toString();
                final String kat = kategoriEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                final String date = dateTV.getText().toString();

                dbManager.insert(name, kat, desc, date);

                Intent main = new Intent(AddCountryActivity.this, CountryListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Toast.makeText(this, "Catatan Tersimpan", Toast.LENGTH_SHORT).show();

                startActivity(main);
                break;
        }
    }
}
//Pengerjaan 5/06/2021