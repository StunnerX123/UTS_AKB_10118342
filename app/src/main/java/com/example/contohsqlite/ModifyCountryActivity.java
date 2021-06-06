package com.example.contohsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class ModifyCountryActivity extends Activity implements OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText katText;
    private EditText descText;
    private TextView dateTV;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);
        katText = (EditText) findViewById(R.id.kategori_edittext);
        dateTV = (TextView) findViewById(R.id.date_textview);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        dateTV.setText(currentDate);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String kat = intent.getStringExtra("kat");
        String desc = intent.getStringExtra("desc");
        String date = intent.getStringExtra("date");

        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);
        katText.setText(kat);
        dateTV.setText(date);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                String title = titleText.getText().toString();
                String kat = katText.getText().toString();
                String desc = descText.getText().toString();
                String date = dateTV.getText().toString();

                dbManager.update(_id, title, kat, desc, date);
                Toast.makeText(this, "Catatan Diperbaharui", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                Toast.makeText(this, "Catatan Dihapus", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;
        }
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), CountryListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
//Pengerjaan 5/06/2021